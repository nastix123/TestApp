@file:Suppress("UNREACHABLE_CODE")
package by.eapp.testapp.repo

import by.eapp.testapp.data.remote.ImageAPIService
import by.eapp.testapp.model.imageDetail.ImageDetailResponse
import by.eapp.testapp.model.searching.Response
import by.eapp.testapp.func.Resource
import by.eapp.testapp.model.chips.CollectionResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ImagesRepository @Inject constructor(
    private val apiService: ImageAPIService
) {
    suspend fun getCollections(collection:  List<*>): Resource<List<CollectionResponse>> {
        return try {
            val response = apiService.getCollections(7)
            return Resource.Success(response)
        }
        catch (e: Exception) {
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
    }}
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

