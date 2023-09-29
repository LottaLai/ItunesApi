package com.lotta.itunesapi.model

data class ITunesSearch(
    val resultCount: Int,
    val results: List<MediaModel>
)