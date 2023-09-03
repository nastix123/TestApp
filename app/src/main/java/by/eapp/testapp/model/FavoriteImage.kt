package by.eapp.testapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_images")
data class FavoriteImage (
        val favorite_image_url: String,
        @PrimaryKey(autoGenerate = false)
        val  favorite_image_id: Int,

        )
