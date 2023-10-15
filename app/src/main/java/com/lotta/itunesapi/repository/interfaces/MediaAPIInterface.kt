package com.lotta.itunesapi.repository.interfaces

import androidx.paging.PagingData
import com.lotta.itunesapi.model.Track
import io.reactivex.rxjava3.core.Flowable

interface MediaAPIInterface {
    fun getSearchTracksResult(query: String): Flowable<PagingData<Track>>
}