package com.lotta.itunesapi.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {
    @Provides
    @Singleton
    fun provideContext(@ActivityContext context: Context): Context = context
}