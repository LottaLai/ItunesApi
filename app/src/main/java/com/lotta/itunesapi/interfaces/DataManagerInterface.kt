package com.lotta.itunesapi.interfaces

import com.lotta.itunesapi.room.Track
import io.reactivex.rxjava3.core.Completable

interface DataManagerInterface {
    fun getBookmarkByID(trackId: Int): Track?
    fun insertFavorite(model: Track): Completable
    fun updateFavorite(model: Track): Completable
    fun deleteFavorite(model: Track): Completable
    fun deleteAllFavorite()
    fun getAllFavorite(): MutableList<Track>
}