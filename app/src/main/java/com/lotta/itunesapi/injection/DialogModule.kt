package com.lotta.itunesapi.injection

import android.content.Context
import com.lotta.itunesapi.ui.dialog.LoadingDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object DialogModule {
    @Provides
    fun provideLoadingDialog(@ActivityContext context: Context): LoadingDialog = LoadingDialog(context)
}