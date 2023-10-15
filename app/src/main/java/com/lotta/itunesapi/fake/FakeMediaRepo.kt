package com.lotta.itunesapi.fake

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.lotta.itunesapi.paging.ITunesPagingSource
import com.lotta.itunesapi.repository.interfaces.MediaAPIInterface
import com.lotta.itunesapi.api.ITunesApiService

//class FakeMediaRepo(
//    private val apiService: ITunesApiService
//): MediaAPIInterface {
//    override fun getSearchTracksResult(query: String) = Pager(
//        config = PagingConfig(
//            pageSize = 20,
//            enablePlaceholders = false,
//            prefetchDistance = 5
//        ),
//        pagingSourceFactory = {
//            ITunesPagingSource(apiService, query)
//        }
//    ).flow
//}