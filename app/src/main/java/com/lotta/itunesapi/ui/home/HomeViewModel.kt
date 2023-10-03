package com.lotta.itunesapi.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.lotta.itunesapi.configuration.DataManager
import com.lotta.itunesapi.model.FilterModel
import com.lotta.itunesapi.model.MediaRepo
import com.lotta.itunesapi.port.DataManagerInterface
import com.lotta.itunesapi.port.MediaRepoInterface
import com.lotta.itunesapi.room.Track
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val dataManager: DataManagerInterface,
    private val repo: MediaRepoInterface
) : ViewModel() {
    var mediaFilterList = MutableLiveData<MutableList<FilterModel>>()
    var termQuery = MutableLiveData("")
    var tracks = termQuery.switchMap { queryString ->
        repo.getSearchTracksResult(queryString).cachedIn(viewModelScope)
    }
    var filter = MutableLiveData<PagingData<Track>>()

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
            val filterModel = FilterModel(enLanguage[x], curLanguage[x])
            if(x == 0) filterModel.isClicked = true
            filterList.add(filterModel)
        }
        mediaFilterList.value = filterList
    }
}