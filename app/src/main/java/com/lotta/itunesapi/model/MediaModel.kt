package com.lotta.itunesapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media")
data class MediaModel(
    @PrimaryKey val id: Int
)