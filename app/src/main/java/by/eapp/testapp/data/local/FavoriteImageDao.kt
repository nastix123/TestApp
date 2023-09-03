package by.eapp.testapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import by.eapp.testapp.model.FavoriteImage
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteImage(favoriteImage:FavoriteImage)

    @Delete
    fun deleteFavoriteImage(favoriteImage:FavoriteImage)

    @Query("SELECT * FROM favorite_images ORDER BY id")
    fun getAllFavoriteImages(): Flow<List<FavoriteImage>>
}
