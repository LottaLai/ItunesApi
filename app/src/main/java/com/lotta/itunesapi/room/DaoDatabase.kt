package com.lotta.itunesapi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lotta.itunesapi.util.DateConverter

@Database(
    entities = [
        Track::class,
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(DateConverter::class)
abstract class DaoDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}