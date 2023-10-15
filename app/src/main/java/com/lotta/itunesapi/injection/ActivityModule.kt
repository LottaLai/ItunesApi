package com.lotta.itunesapi.injection

import android.app.Activity
import android.content.Context
import com.lotta.itunesapi.ui.main.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {
    @Provides
    fun provideContext(@ActivityContext context: Context): Context = context

}