package com.lotta.itunesapi.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lotta.itunesapi.ui.home.repository.interfaces.MediaRepositoryInterface
import com.lotta.itunesapi.model.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repo: MediaRepositoryInterface
) : ViewModel() {
    var favorites = MutableLiveData<List<Track>>()

    fun getAllFavorites(){
        repo.getAllFavorite()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                favorites.value = it
            }
    }
}