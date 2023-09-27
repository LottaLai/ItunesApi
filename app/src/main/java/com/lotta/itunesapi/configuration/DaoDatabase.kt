package com.lotta.itunesapi.configuration

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lotta.itunesapi.model.FavoritesDao
import com.lotta.itunesapi.model.MediaModel
import com.lotta.itunesapi.util.DateConverter

@Database(
    entities = [
        MediaModel::class,
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(DateConverter::class)
abstract class DaoDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao

    companion object {
        private var INSTANCE: DaoDatabase? = null

        fun buildDatabase(context: Context): DaoDatabase =
            INSTANCE ?: synchronized(DaoDatabase::class.java) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    DaoDatabase::class.java,
                    "ITunes.db"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
    }
}