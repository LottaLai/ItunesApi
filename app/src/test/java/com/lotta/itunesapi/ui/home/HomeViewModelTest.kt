package com.lotta.itunesapi.ui.home

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.lotta.itunesapi.api.ApiContainer
import com.lotta.itunesapi.fake.FakeDataManager
import com.lotta.itunesapi.fake.FakeMediaRepo
import com.lotta.itunesapi.retrofitapi.ITunesApiService
import com.lotta.itunesapi.room.DaoDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModelTest{
    private lateinit var viewModel: HomeViewModel
    private lateinit var database: DaoDatabase
    private lateinit var dataManager: FakeDataManager
    private lateinit var mediaRepo: FakeMediaRepo
    private lateinit var apiService: ITunesApiService

    @Before
    fun setup(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            DaoDatabase::class.java
        ).allowMainThreadQueries().build()
        apiService = Retrofit.Builder()
            .baseUrl(ApiContainer.endpoint())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build())
            .build()
            .create(ITunesApiService::class.java)

        dataManager = FakeDataManager(database)
        mediaRepo = FakeMediaRepo(apiService)
        viewModel = HomeViewModel(mediaRepo)
    }

    @Test
    fun `a`(){
        viewModel.tracks
    }
}