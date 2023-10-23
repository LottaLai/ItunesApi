package com.lotta.itunesapi.injection

import com.lotta.itunesapi.ui.home.repository.interfaces.MediaRepositoryInterface
import com.lotta.itunesapi.ui.home.repository.MediaRepository
import com.lotta.itunesapi.api.ITunesApiService
import com.lotta.itunesapi.room.DaoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMediaRepository(apiService: ITunesApiService, database: DaoDatabase): MediaRepositoryInterface =
        MediaRepository(apiService, database)
}