package by.eapp.testapp.data.db.database.database.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.eapp.testapp.data.db.database.ImagesRemoteKey

@Dao
interface ImageRemoteKeyDao {
    @Query("SELECT * FROM photos_remote_keys_table WHERE id=:id")
    suspend fun getRemoteKey(id:Int): ImagesRemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<ImagesRemoteKey>)

    @Query("DELETE FROM photos_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}