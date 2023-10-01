package by.eapp.testapp.feature_images.domain.model.image_List

import by.eapp.testapp.feature_images.domain.model.image_List.Image
import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("next_page")
    val nextPage: String,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("photos")
    val photos: List<Image>,
    @SerializedName("total_results")
    val totalResults: Int
)
