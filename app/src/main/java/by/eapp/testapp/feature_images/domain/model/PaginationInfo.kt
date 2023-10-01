package by.eapp.testapp.feature_images.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images_paging_info")
data class PaginationInfo (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)