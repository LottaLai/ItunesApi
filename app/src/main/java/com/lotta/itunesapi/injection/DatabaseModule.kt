package com.lotta.itunesapi.injection

import android.content.Context
import androidx.room.Room
import com.lotta.itunesapi.room.DaoDatabase
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

}