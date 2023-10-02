package com.lotta.itunesapi.ui.mediaDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lotta.itunesapi.configuration.DataManager
import com.lotta.itunesapi.model.MediaRepo
import com.lotta.itunesapi.room.Track
import javax.inject.Inject

class MediaDetailsViewModel @Inject constructor(
    private val dataManager: DataManager,
    private val repo: MediaRepo
) : ViewModel() {
    var isBookmarked = MutableLiveData(false)

    fun getBookmarked(track: Track){
        isBookmarked.value = (dataManager.getBookmarkByID(track.trackId) != null)
    }

    fun setBookmark(track: Track) {
        if(isBookmarked.value == true){
            deleteBookmark(track)
        }else{
            insertBookmark(track)
        }
    }

    private fun insertBookmark(track: Track){
        dataManager.insertFavorite(track).subscribe({
            println("INSERT: SUCCESS" )
            isBookmarked.postValue(true)
        },{
            println("INSERT: $it")
        })
    }

    private fun deleteBookmark(track: Track){
        dataManager.deleteFavorite(track).subscribe({
            println("DELETE: SUCCESS" )
            isBookmarked.postValue(false)
        },{
            println("DELETE: $it")
        })
    }
}