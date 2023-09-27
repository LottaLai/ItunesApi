package com.lotta.itunesapi.configuration

import com.lotta.itunesapi.model.MediaModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DataManager @Inject constructor(
    private val database: DaoDatabase
) {
    fun insertFavorite(model: MediaModel): Completable {
        return Completable.fromAction {
            database.favoritesDao().insert(model)
        }
    }

    fun updateFavorite(model: MediaModel): Completable {
        return Completable.fromAction {
            database.favoritesDao().update(model)
        }
    }

    fun deleteFavorite(model: MediaModel): Completable {
        return Completable.fromAction {
            database.favoritesDao().delete(model)
        }
    }

    fun deleteAllFavorite() {
        Completable.fromAction {
            database.favoritesDao().deleteAll()
        }.subscribeOn(Schedulers.io()).subscribe()
    }

    fun getAllFavorite(): Flowable<MutableList<MediaModel>> {
        return database.favoritesDao().getAll()
    }

}