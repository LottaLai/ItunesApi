package com.lotta.itunesapi.configuration

import com.lotta.itunesapi.MainActivity
import com.lotta.itunesapi.ui.favorites.FavoritesFragment
import com.lotta.itunesapi.ui.home.HomeFragment
import com.lotta.itunesapi.ui.mediaDetails.MediaDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: MediaDetailsFragment)
    fun inject(fragment: FavoritesFragment)
}