package com.lotta.itunesapi.repository.interfaces

import androidx.paging.PagingData
import com.lotta.itunesapi.model.Track
import kotlinx.coroutines.flow.Flow

interface MediaAPIInterface {
    fun getSearchTracksResult(query: String): Flow<PagingData<Track>>
}