package by.eapp.testapp.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ImageDao {
    @Query()
    fun getAll(): FLow
}