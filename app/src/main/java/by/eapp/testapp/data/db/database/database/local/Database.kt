package by.eapp.testapp.data.db.database.database.local

import androidx.room.Database
import androidx.room.RoomDatabase
import by.eapp.testapp.data.db.database.ImagesRemoteKey
import by.eapp.testapp.data.db.database.imageList.Image

@Database( entities = [Image::class, ImagesRemoteKey::class],
    version = 1,
    exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun remoteKeysDao(): ImageRemoteKeyDao
    abstract fun imageDao(): ImageDao

}