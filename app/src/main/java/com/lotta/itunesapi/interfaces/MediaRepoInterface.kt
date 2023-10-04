package com.lotta.itunesapi.interfaces

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.lotta.itunesapi.room.Track
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface MediaRepoInterface {
    fun getSearchTracksResult(query: String): Flow<PagingData<Track>>
}