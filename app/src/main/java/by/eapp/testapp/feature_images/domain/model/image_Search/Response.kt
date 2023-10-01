package by.eapp.testapp.feature_images.domain.model.image_Search

import by.eapp.testapp.feature_images.domain.model.image_Search.Photo
import com.google.gson.annotations.SerializedName

data class Response (
    @SerializedName("next_page")
    val nextPage: String,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("photos")
    val photos: List<Photo>,
    @SerializedName("total_results")
    val totalResults: Int
)