package by.eapp.testapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import by.eapp.testapp.model.FavoriteImage
import by.eapp.testapp.model.ImagesRemoteKey
import by.eapp.testapp.model.imageList.Image

@Database( entities = [Image::class, ImagesRemoteKey::class, FavoriteImage::class],
    version = 1,
    exportSchema = false)
    abstract class Database: RoomDatabase() {
    abstract fun remoteKeysDao(): ImageRemoteKeyDao
    abstract fun imageDao(): ImageDao
    abstract fun favoriteImageDao(): FavoriteImageDao

}