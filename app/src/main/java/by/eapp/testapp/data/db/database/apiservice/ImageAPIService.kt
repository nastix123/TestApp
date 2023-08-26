package by.eapp.testapp.data.db.database.apiservice

import retrofit2.http.Query
import by.eapp.testapp.data.db.database.imageDetail.ImageDetailResponse
import by.eapp.testapp.data.db.database.searching.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ImageAPIService {
    @Headers("fSIEuzHEyEAdGmmoxdLLaMJvz3yY9l6hLpeAkCKiV1dE1XrFw7aqm2SD")
    @GET("curated")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 30
    ): Response
    @Headers("fSIEuzHEyEAdGmmoxdLLaMJvz3yY9l6hLpeAkCKiV1dE1XrFw7aqm2SD")
    @GET("photos/{id}")
    suspend fun getImageDetails(
        @Path("id") photoId: Int
    ): ImageDetailResponse
    @Headers("fSIEuzHEyEAdGmmoxdLLaMJvz3yY9l6hLpeAkCKiV1dE1XrFw7aqm2SD")
    @GET("search")
    suspend fun searchPhotos(
        @Query("query") photoQuery: String,
        @Query("per_page") perPage: Int = 30
    ): Response


}