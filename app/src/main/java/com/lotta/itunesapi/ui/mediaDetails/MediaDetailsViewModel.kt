package com.lotta.itunesapi.ui.mediaDetails

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
class MediaDetailsViewModel @Inject constructor(
    private val dataManager: DataManagerInterface,
    private val repo: MediaRepoInterface
) : ViewModel() {
    var isBookmarked = MutableLiveData(false)

    fun getBookmarked(track: Track) {
        dataManager.getBookmarkByID(track.trackId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                isBookmarked.value = true
            }
    }

    fun setBookmark(track: Track) {
        if (isBookmarked.value == true) {
            deleteBookmark(track)
        } else {
            insertBookmark(track)
        }
    }

    private fun insertBookmark(track: Track) {
        dataManager.insertFavorite(track).subscribe({
            println("INSERT: SUCCESS")
            isBookmarked.postValue(true)
        }, {
            println("INSERT: $it")
        })
    }

    private fun deleteBookmark(track: Track) {
        dataManager.deleteFavorite(track).subscribe({
            println("DELETE: SUCCESS")
            isBookmarked.postValue(false)
        }, {
            println("DELETE: $it")
        })
    }
}