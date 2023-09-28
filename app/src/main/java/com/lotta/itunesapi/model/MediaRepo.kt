package com.lotta.itunesapi.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.lotta.itunesapi.paging.MediaPagingSource
import com.lotta.itunesapi.retrofitapi.ApiService
import javax.inject.Inject

class MediaRepo @Inject constructor(val apiService: ApiService) {
    val pager = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { MediaPagingSource(apiService) }
    )
}