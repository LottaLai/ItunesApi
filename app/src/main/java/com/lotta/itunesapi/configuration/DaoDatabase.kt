package com.lotta.itunesapi.configuration

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lotta.itunesapi.model.FavoritesDao
import com.lotta.itunesapi.model.MediaModel

@Database(
    entities = [
        MediaModel::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class DaoDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao

    companion object {
        private var INSTANCE: DaoDatabase? = null

        fun buildDatabase(context: Context): DaoDatabase {
            if (INSTANCE == null) {
                synchronized(DaoDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            DaoDatabase::class.java,
                            "ITunes.db"
                        )
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}