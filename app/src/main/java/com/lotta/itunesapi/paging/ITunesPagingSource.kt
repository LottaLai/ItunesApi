package com.lotta.itunesapi.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.lotta.itunesapi.model.Track
import com.lotta.itunesapi.api.ITunesApiService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ITunesPagingSource(
    private val apiService: ITunesApiService,
    private val term: String
) : RxPagingSource<Int, Track>() {
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Track>> {
        val page = params.key ?: 0
        val pageSize = 20

        return apiService.searchTracks(
            term = term,
            offset = page * pageSize,
            limit = pageSize
        ).subscribeOn(Schedulers.io())
            .map<LoadResult<Int, Track>> { response ->
                LoadResult.Page(
                    data = response.results,
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = if (response.results.isEmpty()) null else page + 1,
                )
            }.onErrorReturn { error ->
                LoadResult.Error(error)
            }
    }

    override fun getRefreshKey(state: PagingState<Int, Track>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
}
}