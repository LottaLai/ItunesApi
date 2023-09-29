package com.lotta.itunesapi.configuration

import com.lotta.itunesapi.api.ApiContainer
import com.lotta.itunesapi.model.MediaRepo
import com.lotta.itunesapi.retrofitapi.ITunesApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideApiService(): ITunesApiService = Retrofit.Builder()
        .baseUrl(ApiContainer.endpoint())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build())
        .build()
        .create(ITunesApiService::class.java)

    @Provides
    @Singleton
    fun provideMediaRepo(apiService: ITunesApiService): MediaRepo = MediaRepo(apiService)
}