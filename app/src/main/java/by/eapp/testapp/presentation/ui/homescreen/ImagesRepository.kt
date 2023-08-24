package by.eapp.testapp.presentation.ui.homescreen

import by.eapp.testapp.data.db.database.apiservice.ImageAPIService
import by.eapp.testapp.data.db.database.imageDetail.ImageDetailResponse
import by.eapp.testapp.data.db.database.searching.Response
import by.eapp.testapp.func.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ImagesRepository @Inject constructor(
    private val apiService: ImageAPIService
) {
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
