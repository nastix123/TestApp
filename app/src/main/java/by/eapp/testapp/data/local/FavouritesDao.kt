package by.eapp.testapp.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import by.eapp.testapp.model.FavoriteImage
import kotlinx.coroutines.flow.Flow


@Dao
interface FavouritesDao {
    @Upsert
    fun addFavoriteImage()

    @Query("DELETE * FROM favorite_images")
    fun deleteFavoriteImage()

    @Query("SELECT * FROM favorite_images")
    fun getAllFavoriteImages(): Flow<List<FavoriteImage>>
}
