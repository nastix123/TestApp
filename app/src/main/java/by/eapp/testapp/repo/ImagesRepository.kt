@file:Suppress("UNREACHABLE_CODE")

package by.eapp.testapp.repo

import by.eapp.testapp.data.local.FavoriteImageDao
import by.eapp.testapp.data.remote.ImageAPIService
import by.eapp.testapp.func.Resource
import by.eapp.testapp.model.FavoriteImage
import by.eapp.testapp.model.chips.CollectionResponse
import by.eapp.testapp.model.imageDetail.ImageDetailResponse
import by.eapp.testapp.model.searching.Response
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


class ImagesRepository @Inject constructor(
    private val apiService: ImageAPIService,
    private val favoriteImageDao: FavoriteImageDao
) {

    fun addFavoriteImage(favoriteImage: FavoriteImage) {
        favoriteImageDao.addFavoriteImage(favoriteImage = favoriteImage)
    }
    fun deleteFavoriteImage(favoriteImage: FavoriteImage) {
        favoriteImageDao.deleteFavoriteImage(favoriteImage = favoriteImage)
    }
    fun getAllFavorites():Flow<List<FavoriteImage>>{
    return favoriteImageDao.getAllFavoriteImages()

    }
    val listOfFavoriteImages: Flow<List<FavoriteImage>> = favoriteImageDao.getAllFavoriteImages()
   /* fun isImageInFavorites(imageId = Int, imageURL = String): Boolean {
        return flow {
            val favoritesList = favoriteImageDao.getAllFavoriteImages()
            val isImageFavorite = favoritesList.any {
                it.favorite_image_id == imageId && it.favorite_image_url == imageURL
            }
            emit(isImageFavorite)
        }
    }*/

    suspend fun getCollections(): Resource<CollectionResponse> {
        return try {
            val response = apiService.getCollections(7)
            return Resource.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            when (e) {
                is IOException -> return Resource.Error(
                    "Ensure you have an active internet connection"
                )

                is HttpException -> return Resource.Error(
                    "We're unable to reach servers. Please retry."
                )

                else -> return Resource.Error(
                    e.localizedMessage ?: "An unknown error occurred. Please retry."
                )
            }
        }
    }

    suspend fun getPhotoDetails(photoId: Int): Resource<ImageDetailResponse> {
        return try {
            val response = apiService.getImageDetails(photoId)
            return Resource.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            when (e) {
                is IOException -> return Resource.Error(
                    "Ensure you have an active internet connection"
                )

                is HttpException -> return Resource.Error(
                    "We're unable to reach servers. Please retry."
                )

                else -> return Resource.Error(
                    e.localizedMessage ?: "An unknown error occurred. Please retry."
                )
            }
        }
    }

    suspend fun searchPhoto(photoQuery: String): Resource<Response> {
        return try {
            val result = apiService.searchPhotos(photoQuery)
            return Resource.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            when (e) {
                is IOException -> return Resource.Error(
                    "Ensure you have an active internet connection"
                )

                is HttpException -> return Resource.Error(
                    "We're unable to reach servers. Please retry."
                )

                else -> return Resource.Error(
                    e.localizedMessage ?: "An unknown error occurred. Please retry."
                )
            }
        }
    }
}

