package by.eapp.testapp.data.remote

import retrofit2.http.Query
import by.eapp.testapp.model.imageDetail.ImageDetailResponse
import by.eapp.testapp.model.imageList.ImageResponse
import by.eapp.testapp.model.searching.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ImageAPIService {
    @Headers("Authorization: fSIEuzHEyEAdGmmoxdLLaMJvz3yY9l6hLpeAkCKiV1dE1XrFw7aqm2SD")
    @GET("curated")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 30
    ): ImageResponse
    @Headers("Authorization: fSIEuzHEyEAdGmmoxdLLaMJvz3yY9l6hLpeAkCKiV1dE1XrFw7aqm2SD")
    @GET("photos/{id}")
    suspend fun getImageDetails(
        @Path("id") photoId: Int
    ): ImageDetailResponse
    @Headers("Authorization: fSIEuzHEyEAdGmmoxdLLaMJvz3yY9l6hLpeAkCKiV1dE1XrFw7aqm2SD")
    @GET("search")
    suspend fun searchPhotos(
        @Query("query") photoQuery: String,
        @Query("per_page") perPage: Int = 30
    ): Response


}