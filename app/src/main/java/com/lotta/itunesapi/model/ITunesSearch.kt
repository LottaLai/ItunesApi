package com.lotta.itunesapi.model

import com.lotta.itunesapi.room.Track

data class SearchResult(
    val resultCount: Int,
    val results: List<Track>
)