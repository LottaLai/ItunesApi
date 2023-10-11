package com.lotta.itunesapi.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lotta.itunesapi.interfaces.DataManagerInterface
import com.lotta.itunesapi.interfaces.MediaRepoInterface
import com.lotta.itunesapi.room.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val dataManager: DataManagerInterface,
    private val repo: MediaRepoInterface
) : ViewModel() {
    var favorites = MutableLiveData<List<Track>>()

    fun getAllFavorites(){
        dataManager.getAllFavorite()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                favorites.value = it
            }
    }
}