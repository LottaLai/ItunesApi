package com.lotta.itunesapi.configuration

import android.app.Application
import android.content.Context
import com.lotta.itunesapi.interfaces.DataManagerInterface
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