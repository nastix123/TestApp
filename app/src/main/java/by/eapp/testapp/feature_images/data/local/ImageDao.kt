package by.eapp.testapp.feature_images.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import by.eapp.testapp.feature_images.domain.model.image_List.Image

@Dao
interface ImageDao {
  @Upsert
  fun saveAll(images:List<Image>)

  @Query("SELECT * FROM images_table")
  fun getAll(): PagingSource<Int, Image>

  @Query("DELETE FROM images_table")
  fun deleteAll(): Int
}