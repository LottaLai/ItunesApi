package com.lotta.itunesapi.ui.mediaDetails

import androidx.lifecycle.ViewModel
import com.lotta.itunesapi.configuration.DataManager
import com.lotta.itunesapi.model.MediaRepo
import com.lotta.itunesapi.model.Track
import javax.inject.Inject

class MediaDetailsViewModel @Inject constructor(
    private val dataManager: DataManager,
    private val repo: MediaRepo
) : ViewModel() {

    fun getBookMarked(track: Track) : Boolean{
        return dataManager.getBookmarkByID(track.trackId) == null
    }

    fun setBookmark(track: Track) {
        dataManager.insertFavorite(track).subscribe({
            println("INSERT: SUCCESS" )
        },{
            println("INSERT: $it")
        })
    }
}