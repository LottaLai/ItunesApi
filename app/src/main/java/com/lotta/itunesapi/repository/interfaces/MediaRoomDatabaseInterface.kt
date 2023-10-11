package com.lotta.itunesapi.repository.interfaces

import com.lotta.itunesapi.model.Track
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface MediaRoomDatabaseInterface {
    fun getBookmarkByID(trackId: Int): Flowable<Track>
    fun insertFavorite(model: Track): Completable
    fun updateFavorite(model: Track): Completable
    fun deleteFavorite(model: Track): Completable
    fun deleteAllFavorite()
    fun getAllFavorite(): Flowable<List<Track>>
}