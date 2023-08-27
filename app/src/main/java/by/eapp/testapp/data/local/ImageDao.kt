package by.eapp.testapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import by.eapp.testapp.model.imageList.Image
import by.eapp.testapp.model.searching.Photo

@Dao
interface ImageDao {
  @Upsert
  suspend fun saveAll(images:List<Image>)

  @Query("SELECT * FROM images_table")
  fun getAll(): PagingSource<Int, Image>

  @Query("DELETE FROM images_table")
  suspend fun deleteAll()

}