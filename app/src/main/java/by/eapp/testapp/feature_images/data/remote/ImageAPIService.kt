package by.eapp.testapp.feature_images.data.remote

import by.eapp.testapp.func.Base
import by.eapp.testapp.model.chips.CollectionResponse
import retrofit2.http.Query
import by.eapp.testapp.feature_images.domain.model.ImageDetailResponse
import by.eapp.testapp.feature_images.domain.model.image_List.ImageResponse
import by.eapp.testapp.feature_images.domain.model.image_Search.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ImageAPIService {
    @Headers("Authorization: ${Base.API_KEY}")
    @GET("curated")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 30
    ): ImageResponse
   @Headers("Authorization: ${Base.API_KEY}")
    @GET("photos/{id}")
    suspend fun getImageDetails(
        @Path("id") photoId: Int
    ): ImageDetailResponse
    @Headers("Authorization: ${Base.API_KEY}")
    @GET("search")
    suspend fun searchPhotos(
        @Query("query") photoQuery: String,
        @Query("per_page") perPage: Int = 30
    ): Response

    @Headers("Authorization: ${Base.API_KEY}")
    @GET("collections")
    suspend fun getCollections(
        @Query("per_page") perPage: Int = 7
    ) :CollectionResponse

}



