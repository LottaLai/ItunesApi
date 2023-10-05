package com.lotta.itunesapi.configuration

import androidx.lifecycle.LiveData
import com.lotta.itunesapi.interfaces.DataManagerInterface
import com.lotta.itunesapi.room.DaoDatabase
import com.lotta.itunesapi.room.Track
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class DataManager(
    private val database: DaoDatabase
) : DataManagerInterface {
    override fun getBookmarkByID(trackId: Int): LiveData<Track>? {
        return database.favoritesDao().get(trackId)
    }

    override fun insertFavorite(model: Track): Completable {
        return Completable.fromAction {
            database.favoritesDao().insert(model)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateFavorite(model: Track): Completable {
        return Completable.fromAction {
            database.favoritesDao().update(model)
        }
    }

    override fun deleteFavorite(model: Track): Completable {
        return Completable.fromAction {
            database.favoritesDao().delete(model)
        }
    }

    override fun deleteAllFavorite() {
        Completable.fromAction {
            database.favoritesDao().deleteAll()
        }.subscribeOn(Schedulers.io()).subscribe()
    }

    override fun getAllFavorite(): LiveData<MutableList<Track>> {
        return database.favoritesDao().getAll()
    }

}