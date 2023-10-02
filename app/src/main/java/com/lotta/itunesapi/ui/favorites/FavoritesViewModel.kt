package com.lotta.itunesapi.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lotta.itunesapi.configuration.DataManager
import com.lotta.itunesapi.model.MediaRepo
import com.lotta.itunesapi.model.Track
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val dataManager: DataManager,
    private val repo: MediaRepo
) : ViewModel() {
    var favorites = MutableLiveData<List<Track>>()

    fun getAllFavorites(){
        dataManager.getAllFavorite().subscribe({
            favorites.postValue(it)
        },{
            println("GET ALL: $it")
        })
    }
}