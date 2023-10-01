package by.eapp.testapp.feature_images.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import by.eapp.testapp.feature_images.domain.model.FavoriteImage
import by.eapp.testapp.feature_images.domain.model.PaginationInfo

import by.eapp.testapp.feature_images.domain.model.image_List.Image

@Database( entities = [Image::class, PaginationInfo::class, FavoriteImage::class],
    version = 1,
    exportSchema = false)
    abstract class Database: RoomDatabase() {
    abstract fun remoteKeysDao(): ImageRemoteKeyDao
    abstract fun imageDao(): ImageDao
    abstract fun favoriteImageDao(): FavoriteImageDao

}