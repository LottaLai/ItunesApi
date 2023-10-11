package com.lotta.itunesapi.model

data class SearchResult(
    val resultCount: Int,
    val results: List<Track>
)