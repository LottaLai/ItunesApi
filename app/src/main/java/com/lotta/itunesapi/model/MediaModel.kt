package com.lotta.itunesapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "favorites")
data class MediaModel(
    @PrimaryKey
    val id: Int,
    val releaseDate: Date
)