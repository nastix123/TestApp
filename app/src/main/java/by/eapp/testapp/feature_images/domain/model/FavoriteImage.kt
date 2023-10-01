package by.eapp.testapp.feature_images.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_images")
data class FavoriteImage(
    val favorite_image_url: String,
    @PrimaryKey(autoGenerate = false)
    val favorite_image_id: Int,
)

