package com.lotta.itunesapi.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.lotta.itunesapi.model.FilterModel
import com.lotta.itunesapi.interfaces.DataManagerInterface
import com.lotta.itunesapi.interfaces.MediaRepoInterface
import com.lotta.itunesapi.room.Track
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val dataManager: DataManagerInterface,
    private val repo: MediaRepoInterface
) : ViewModel() {
    var mediaFilterList = MutableLiveData<MutableList<FilterModel>>()
    var tracks = MutableLiveData<PagingData<Track>>()
    var filter = MutableLiveData<PagingData<Track>>()

    fun getSearchResult(termQuery: String){
        viewModelScope.launch {
            repo.getSearchTracksResult(termQuery).cachedIn(viewModelScope).collectLatest{ data ->
                tracks.value = data
            }
        }
    }

    fun filterTracks(filterString: String) {
        filter.value = if (filterString == "all") {
            tracks.value
        }
        else {
            tracks.value?.filter { track ->
                track.kind == filterString
            }
        }
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