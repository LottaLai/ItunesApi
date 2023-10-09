package com.lotta.itunesapi.configuration

import android.content.Context
import androidx.room.Room
import com.lotta.itunesapi.interfaces.DataManagerInterface
import com.lotta.itunesapi.room.DaoDatabase
import com.lotta.itunesapi.room.FavoritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): DaoDatabase =
        Room.databaseBuilder(context,
            DaoDatabase::class.java,
            "ITunes.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideDataManager(daoDatabase: DaoDatabase): DataManagerInterface =
        DataManager(daoDatabase)

    @Provides
    fun provideFavoritesDao(database: DaoDatabase): FavoritesDao = database.favoritesDao()
}