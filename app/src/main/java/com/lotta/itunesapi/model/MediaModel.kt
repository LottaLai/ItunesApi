package com.lotta.itunesapi.model

import androidx.room.Entity

@Entity(tableName = "favorites", primaryKeys = ["artistId", "collectionId", "trackId"])
data class MediaModel(
    val artistId: Int,
    val collectionId: Int,
    val trackId: Int,
    val wrapperType: String,
    val kind: String,
    val artistName: String,
    val collectionName: String,
    val trackName: String,
    val collectionCensoredName: String,
    val trackCensoredName: String,
    val artistViewUrl: String,
    val collectionViewUrl: String,
    val trackViewUrl: String,
    val previewUrl: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val artworkUrl100: String,
    val collectionPrice: Double,
    val trackPrice: Double,
    val releaseDate: String,
    val collectionExplicitness: String,
    val trackExplicitness: String,
    val discCount: Int,
    val discNumber: Int,
    val trackCount: Int,
    val trackNumber: Int,
    val trackTimeMillis: Int,
    val country: String,
    val currency: String,
    val primaryGenreName: String,
    val isStreamable: Boolean
)