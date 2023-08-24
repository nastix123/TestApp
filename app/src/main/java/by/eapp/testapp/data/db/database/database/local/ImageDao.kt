package by.eapp.testapp.data.db.database.database.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import by.eapp.testapp.data.db.database.searching.Photo

@Dao
interface ImageDao {
  @Upsert
  suspend fun saveAll(images:List<Photo>)

  @Query("SELECT * FROM images_table")
  suspend fun getAll():
          @Query("DELETE *FROM images_table")
          suspend fun deleteAll()

}