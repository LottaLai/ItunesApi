package com.lotta.itunesapi.ui.home.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava3.flowable
import com.lotta.itunesapi.ui.home.repository.interfaces.MediaRepositoryInterface
import com.lotta.itunesapi.paging.ITunesPagingSource
import com.lotta.itunesapi.api.ITunesApiService
import com.lotta.itunesapi.model.Track
import com.lotta.itunesapi.room.DaoDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MediaRepository @Inject constructor(
    private val apiService: ITunesApiService,
    private val database: DaoDatabase
) : MediaRepositoryInterface {
    /**
     * retrofit api implementation
     */
    override fun getSearchTracksResult(query: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 200,
            prefetchDistance = 5,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = {
            ITunesPagingSource(apiService, query)
        }
    ).flowable

    /**
     * room database implementation
     */
    override fun getBookmarkByID(trackId: Int): Flowable<Track> {
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

    override fun getAllFavorite(): Flowable<List<Track>> {
        return database.favoritesDao().getAll()
    }
}