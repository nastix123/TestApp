package by.eapp.testapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "photos_remote_keys_table")
data class ImagesRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
