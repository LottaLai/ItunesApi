package com.lotta.itunesapi.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.lotta.itunesapi.paging.MediaPagingSource
import com.lotta.itunesapi.retrofitapi.ApiService

class MediaRepo (private val apiService: ApiService) {
    val pager = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { MediaPagingSource(apiService) }
    )

//    fun getUsers(): Single<List<MediaModel>> {
//        return apiService.getSearchResults(1, 2)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//    }
}