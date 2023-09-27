package com.lotta.itunesapi.configuration

import android.app.Application
import androidx.work.Configuration

class ITunesApp: Application(), Configuration.Provider {

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().build()
    }

    companion object {
        @get:Synchronized
        lateinit var application: ITunesApp
            private set
    }

}