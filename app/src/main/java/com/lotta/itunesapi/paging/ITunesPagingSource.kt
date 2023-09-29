package com.lotta.itunesapi.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lotta.itunesapi.model.Track
import com.lotta.itunesapi.retrofitapi.ITunesApiService
import io.reactivex.rxjava3.schedulers.Schedulers

class ITunesPagingSource(
    private val apiService: ITunesApiService,
    private val term: String
) : PagingSource<Int, Track>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
        val page = params.key ?: 0
        val pageSize = 20
        return try {
            val response = apiService.searchTracks(
                term,
                page * pageSize,
                pageSize
            ).blockingGet()

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Track>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}