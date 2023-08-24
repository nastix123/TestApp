package by.eapp.testapp.data.db.database.database.local

import androidx.room.PrimaryKey

data class ImageEntity (
    @PrimaryKey(autoGenerate = true) val ID:Int,
    val prevPage: Int,
    val nextPage: Int
    )