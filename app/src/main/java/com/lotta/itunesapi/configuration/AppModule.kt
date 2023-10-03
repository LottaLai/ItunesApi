package com.lotta.itunesapi.configuration

import android.app.Application
import android.content.Context
import com.lotta.itunesapi.model.MediaRepo
import com.lotta.itunesapi.port.AppComponent
import com.lotta.itunesapi.port.DataManagerInterface
import com.lotta.itunesapi.port.MediaRepoInterface
import com.lotta.itunesapi.retrofitapi.ITunesApiService
import com.lotta.itunesapi.room.DaoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideDatabase(): DaoDatabase = DaoDatabase.buildDatabase(application)

    @Provides
    @Singleton
    fun provideDataManager(daoDatabase: DaoDatabase): DataManagerInterface = DataManager(daoDatabase)
}