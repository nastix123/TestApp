package by.eapp.testapp.db

import androidx.room.Database

@Database(entities = [ImageEntity::class], version = 2, exportSchema = false)
abstract class database {
    abstract fun imageDao(): ImageDao
}