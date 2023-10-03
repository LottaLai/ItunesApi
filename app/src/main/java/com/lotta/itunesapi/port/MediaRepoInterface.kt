package com.lotta.itunesapi.port

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.lotta.itunesapi.room.Track
import javax.inject.Singleton

@Singleton
interface MediaRepoInterface {
    fun getSearchTracksResult(query: String): LiveData<PagingData<Track>>
}