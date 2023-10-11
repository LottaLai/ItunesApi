package com.lotta.itunesapi.ui.mediaDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lotta.itunesapi.repository.interfaces.MediaRepositoryInterface
import com.lotta.itunesapi.model.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MediaDetailsViewModel @Inject constructor(
    private val repo: MediaRepositoryInterface
) : ViewModel() {
    var isBookmarked = MutableLiveData(false)

    fun getBookmarked(track: Track) {
        repo.getBookmarkByID(track.trackId)
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
        repo.insertFavorite(track).subscribe({
            println("INSERT: SUCCESS")
            isBookmarked.postValue(true)
        }, {
            println("INSERT: $it")
        })
    }

    private fun deleteBookmark(track: Track) {
        repo.deleteFavorite(track).subscribe({
            println("DELETE: SUCCESS")
            isBookmarked.postValue(false)
        }, {
            println("DELETE: $it")
        })
    }
}