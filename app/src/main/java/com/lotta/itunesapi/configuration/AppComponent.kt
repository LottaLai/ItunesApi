package com.lotta.itunesapi.configuration

import com.lotta.itunesapi.MainActivity
import com.lotta.itunesapi.ui.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(homeFragment: HomeFragment)
}