package com.lotta.itunesapi.interfaces

import androidx.paging.PagingData
import com.lotta.itunesapi.room.Track
import kotlinx.coroutines.flow.Flow

interface MediaRepoInterface {
    fun getSearchTracksResult(query: String): Flow<PagingData<Track>>
}