package com.lotta.itunesapi.interfaces

import com.lotta.itunesapi.room.Track
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface DataManagerInterface {
    fun getBookmarkByID(trackId: Int): Flowable<Track>
    fun insertFavorite(model: Track): Completable
    fun updateFavorite(model: Track): Completable
    fun deleteFavorite(model: Track): Completable
    fun deleteAllFavorite()
    fun getAllFavorite(): Flowable<List<Track>>
}