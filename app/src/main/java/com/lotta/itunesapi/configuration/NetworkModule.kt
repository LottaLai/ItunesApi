package com.lotta.itunesapi.configuration

import com.lotta.itunesapi.api.ApiContainer
import com.lotta.itunesapi.model.MediaRepo
import com.lotta.itunesapi.retrofitapi.ApiService
import com.lotta.itunesapi.retrofitapi.RetrofitService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiContainer.endpoint())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMediaRepo(apiService: ApiService): MediaRepo = MediaRepo(apiService)
}