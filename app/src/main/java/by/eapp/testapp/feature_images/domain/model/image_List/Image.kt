package by.eapp.testapp.feature_images.domain.model.image_List

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.eapp.testapp.feature_images.domain.model.image_Search.Src
import com.google.gson.annotations.SerializedName


@Entity(tableName = "images_table")
data class Image(
    @SerializedName("alt")
    val alt: String,
    @SerializedName("avg_color")
    val avgColor: String,
    @SerializedName("height")
    val height: Int,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,
    @SerializedName("src")
    @Embedded
    val src: Src,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
) {

}
