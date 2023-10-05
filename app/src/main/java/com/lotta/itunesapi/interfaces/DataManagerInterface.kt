package com.lotta.itunesapi.interfaces

import androidx.lifecycle.LiveData
import com.lotta.itunesapi.room.Track
import io.reactivex.rxjava3.core.Completable

interface DataManagerInterface {
    fun getBookmarkByID(trackId: Int): LiveData<Track>?
    fun insertFavorite(model: Track): Completable
    fun updateFavorite(model: Track): Completable
    fun deleteFavorite(model: Track): Completable
    fun deleteAllFavorite()
    fun getAllFavorite(): LiveData<MutableList<Track>>
}