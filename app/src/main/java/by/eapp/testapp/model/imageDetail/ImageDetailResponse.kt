package by.eapp.testapp.model.imageDetail

import by.eapp.testapp.model.FavoriteImage
import com.google.gson.annotations.SerializedName

data class ImageDetailResponse(
    @SerializedName("alt")
    val alt: String,
    @SerializedName("avg_color")
    val avgColor: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("liked")
    val liked: Boolean,
    @SerializedName("photographer")
    val photographer: String,
    @SerializedName("photographer_id")
    val photographerId: Int,
    @SerializedName("photographer_url")
    val photographerUrl: String,
    @SerializedName("src")
    val src: Src,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)
fun ImageDetailResponse.toFavoriteImage(): FavoriteImage {
    return FavoriteImage(
        favorite_image_url = this.url,
        favorite_image_id = this.id
    )
}
fun FavoriteImage.toImageDetailResponse(): ImageDetailResponse {
    return ImageDetailResponse(
        alt = "",
        avgColor = "",
        height = 0,
        id = this.favorite_image_id,
        liked = false,
        photographer = "",
        photographerId = 0,
        photographerUrl = "",
        src = Src( landscape = "",
            large = "",
            large2x = "",
            medium = "",
            original = this.favorite_image_url,
            portrait = this.favorite_image_url,
            small = "",
            tiny = ""
        ),
        url = this.favorite_image_url,
        width = 0
    )
}