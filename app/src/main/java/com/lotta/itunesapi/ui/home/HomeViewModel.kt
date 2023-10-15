package com.lotta.itunesapi.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.rxjava3.cachedIn
import androidx.room.util.copy
import com.lotta.itunesapi.model.FilterModel
import com.lotta.itunesapi.model.Track
import com.lotta.itunesapi.repository.interfaces.MediaRepositoryInterface
import com.lotta.itunesapi.ui.main.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MediaRepositoryInterface
) : ViewModel() {
    var mediaFilterList = MutableLiveData<MutableList<FilterModel>>()
    private lateinit var searchResult: PagingData<Track>
    var filterTracks = MutableLiveData<PagingData<Track>>()


    fun getSearchResult(termQuery: String) {
        MainActivity.mainViewState.value = MainActivity.mainViewState.value?.copy(isLoading = true)
        repo.getSearchTracksResult(termQuery)
            .cachedIn(viewModelScope)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ pagingData ->
                searchResult = pagingData
                filterTracks()
                MainActivity.mainViewState.value = MainActivity.mainViewState.value?.copy(
                    isLoading = false,
                    successMessage = "Search complete"
                )
            }, {
                MainActivity.mainViewState.value = MainActivity.mainViewState.value?.copy(
                    isLoading = false,
                    errorMessage = it.toString()
                )
            })
    }

    fun filterTracks(filterString: String? = "all") {
        val data = if (filterString == "all") {
            searchResult
        } else {
            searchResult.filter {
                it.kind == filterString
            }
        }
        filterTracks.value = data
    }

    fun filterList(
        enLanguage: Array<String>,
        curLanguage: Array<String>
    ) {
        val filterList = mutableListOf<FilterModel>()
        for (x in enLanguage.indices) {
            val filterModel = FilterModel(enLanguage[x], curLanguage[x], x == 0)
            filterList.add(filterModel)
        }
        mediaFilterList.value = filterList
    }
}