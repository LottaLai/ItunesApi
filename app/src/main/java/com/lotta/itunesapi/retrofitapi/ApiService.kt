package com.lotta.itunesapi.retrofitapi

import com.lotta.itunesapi.model.MediaModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("items")
    fun getSearchResults(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int)
    : Single<List<MediaModel>>
}