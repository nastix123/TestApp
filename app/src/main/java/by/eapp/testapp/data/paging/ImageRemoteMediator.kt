@file:OptIn(ExperimentalPagingApi::class)

package by.eapp.testapp.data.paging


import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import by.eapp.testapp.model.ImagesRemoteKey
import by.eapp.testapp.data.remote.ImageAPIService
import by.eapp.testapp.data.local.Database
import by.eapp.testapp.model.imageList.Image
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ImagesRemoteMediator @Inject constructor(
    private val dbImages: Database,
    private val apiService: ImageAPIService,
) : RemoteMediator<Int, Image>() {
    private val imagesDao = dbImages.imageDao()
    private val remoteKeysDao = dbImages.remoteKeysDao()




    override suspend fun load(loadType: LoadType, state: PagingState<Int, Image>): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKey?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKey != null
                        )
                    nextPage
                }
            }

            delay(5000L)
            val response = apiService.getImages(page = currentPage)
            val endOfPaginationReached = response.photos.isEmpty()
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            dbImages.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    imagesDao.deleteAll()
                    remoteKeysDao.deleteAllRemoteKeys()
                }

                val keys = response.photos.map { photo ->
                    ImagesRemoteKey(
                        id = photo.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }

                remoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                imagesDao.saveAll(images = response.photos)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            e.printStackTrace()
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            e.printStackTrace()
            return MediatorResult.Error(e)
        } catch (e: Exception) {
            e.printStackTrace()
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Image>
    ): ImagesRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getRemoteKey(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Image>
    ): ImagesRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let {
                remoteKeysDao.getRemoteKey(id = it.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Image>
    ): ImagesRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let {
                remoteKeysDao.getRemoteKey(id = it.id)
            }
    }
}
