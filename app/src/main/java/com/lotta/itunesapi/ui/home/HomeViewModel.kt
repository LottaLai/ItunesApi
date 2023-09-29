package com.lotta.itunesapi.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.lotta.itunesapi.configuration.DataManager
import com.lotta.itunesapi.model.FilterModel
import com.lotta.itunesapi.model.Track
import com.lotta.itunesapi.model.MediaRepo
import com.lotta.itunesapi.model.TrackAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val dataManager: DataManager,
    private val repo: MediaRepo
) : ViewModel() {
    var originList: MutableList<Track> = mutableListOf()
    var songList = MutableLiveData<MutableList<Track>>()
    var mediaFilterList = MutableLiveData<MutableList<FilterModel>>()
    var countryFilterList = MutableLiveData<MutableList<FilterModel>>()

    fun getSearch(
        term: String,
        adapter: TrackAdapter
    ){
        viewModelScope.launch {
            repo.getSearch(term).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    fun filterMediaList(
        kind: String
    ){
        val oldList = originList
        val filterList = if(kind == "ALL") oldList else oldList.filter { it -> it.kind == kind }
        songList.value = filterList.toMutableList()
    }

    fun filterCountryList(
        country: String
    ){
        val oldList = originList
        val filterList = if(country == "ALL") oldList else oldList.filter { it -> it.country == country }
        songList.value = filterList.toMutableList()
    }
}