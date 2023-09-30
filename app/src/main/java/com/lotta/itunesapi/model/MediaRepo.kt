package com.lotta.itunesapi.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.lotta.itunesapi.paging.ITunesPagingSource
import com.lotta.itunesapi.retrofitapi.ITunesApiService

class MediaRepo(
    private val apiService: ITunesApiService
) {
    fun getSearchTracksResult(query: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            prefetchDistance = 5
        ),
        pagingSourceFactory = {
            ITunesPagingSource(apiService, query)
        }
    ).liveData
}