package com.lotta.itunesapi.api

import com.lotta.itunesapi.model.SearchResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApiService {

    @GET("search")
    fun searchTracks(
        @Query("term") term: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Single<SearchResult>
}