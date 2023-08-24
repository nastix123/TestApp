package by.eapp.testapp.data.db.database.apiservice

import androidx.room.Query
import by.eapp.testapp.data.db.database.imageDetail.ImageDetailResponse
import by.eapp.testapp.data.db.database.searching.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageAPIService {
    @GET("curated")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 30
    ): Response

    @GET("photos/{id}")
    suspend fun getImageDetails(
        @Path("id") photoId: Int
    ): ImageDetailResponse

    @GET("search")
    suspend fun searchPhotos(
        @Query("query") photoQuery: String,
        @Query("per_page") perPage: Int = 30
    ): Response

}