package com.lotta.itunesapi.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lotta.itunesapi.model.MediaModel
import com.lotta.itunesapi.retrofitapi.ApiService

class MediaPagingSource(private val apiService: ApiService) : PagingSource<Int, MediaModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MediaModel> {
        try {
            val nextPage = params.key ?: 1
            val pageSize = params.loadSize

            val items = apiService.getMedias(nextPage, pageSize)
            return LoadResult.Page(
                data = items,
                prevKey = null,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MediaModel>): Int? {
        TODO("Not yet implemented")
    }
}