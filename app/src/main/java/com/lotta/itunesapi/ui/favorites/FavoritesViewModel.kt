package com.lotta.itunesapi.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lotta.itunesapi.interfaces.DataManagerInterface
import com.lotta.itunesapi.interfaces.MediaRepoInterface
import com.lotta.itunesapi.room.Track
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val dataManager: DataManagerInterface,
    private val repo: MediaRepoInterface
) : ViewModel() {
    var favorites = MutableLiveData<List<Track>>()

    fun getAllFavorites(){
        favorites.value = dataManager.getAllFavorite().value
    }
}