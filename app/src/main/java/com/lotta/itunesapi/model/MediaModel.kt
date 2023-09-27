package com.lotta.itunesapi.model

import androidx.room.Entity
import java.util.*

@Entity(tableName = "favorites", primaryKeys = ["id"])
data class MediaModel(
    val id: Int,
    val releaseDate: Date
)