package com.lotta.itunesapi.configuration

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ITunesApp: Application()/*, Configuration.Provider*/ {

//    lateinit var appComponent: AppComponent
//
//    override fun onCreate() {
//        super.onCreate()
//        application = this
//        appComponent = DaggerAppComponent
//            .builder()
//            .appModule(AppModule(this))
//            .build()
//    }
//
//    override fun getWorkManagerConfiguration(): Configuration {
//        return Configuration.Builder().build()
//    }
//
//    companion object {
//        @get:Synchronized
//        lateinit var application: ITunesApp
//            private set
//    }

}