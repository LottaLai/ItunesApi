package com.lotta.itunesapi.configuration

import com.lotta.itunesapi.model.Track
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DataManager @Inject constructor(
    private val database: DaoDatabase
) {
    fun getBookmarkByID(trackId: Int): Track? {
        return database.favoritesDao().get(trackId)
    }

    fun insertFavorite(model: Track): Completable {
        return Completable.fromAction {
            database.favoritesDao().insert(model)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun updateFavorite(model: Track): Completable {
        return Completable.fromAction {
            database.favoritesDao().update(model)
        }
    }

    fun deleteFavorite(model: Track): Completable {
        return Completable.fromAction {
            database.favoritesDao().delete(model)
        }
    }

    fun deleteAllFavorite() {
        Completable.fromAction {
            database.favoritesDao().deleteAll()
        }.subscribeOn(Schedulers.io()).subscribe()
    }

    fun getAllFavorite(): Flowable<MutableList<Track>> {
        return database.favoritesDao().getAll()
    }

}