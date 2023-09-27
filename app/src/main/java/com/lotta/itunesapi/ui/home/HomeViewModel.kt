package com.lotta.itunesapi.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lotta.itunesapi.api.ApiContainer
import com.lotta.itunesapi.api.VolleyManager
import com.lotta.itunesapi.configuration.DataManager
import com.lotta.itunesapi.model.MediaModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

class HomeViewModel @Inject constructor(
    private val dataManager: DataManager,
    private val volleyManager: VolleyManager
) : ViewModel() {
    var movieList = MutableLiveData<MutableList<MediaModel>>()
    var podcastList = MutableLiveData<MutableList<MediaModel>>()
    var musicList = MutableLiveData<MutableList<MediaModel>>()
    var musicVideoList = MutableLiveData<MutableList<MediaModel>>()
    var audiobookList = MutableLiveData<MutableList<MediaModel>>()
    var shortFilmList = MutableLiveData<MutableList<MediaModel>>()
    var tvShowList = MutableLiveData<MutableList<MediaModel>>()
    var softwareList = MutableLiveData<MutableList<MediaModel>>()
    var ebookList = MutableLiveData<MutableList<MediaModel>>()

    fun fetchData(
        term: String = "",
        limit: Int = 20,
        entity: String = "",
        offset: Int = 0
    ) {
        viewModelScope.launch {
            val urlBuilder = StringBuilder()
                .append(ApiContainer.endpoint())
                .append("search?")
                .append(if (term.isNotEmpty()) "&term=$term" else "")
                .append(if (entity.isNotEmpty()) "&entity=$entity" else "")
                .append(if (limit > 0) "&limit=$limit" else "")
                .append(if (offset > 0) "&offset=$offset" else "")
            val url = urlBuilder.toString()
            val time = measureTimeMillis {
                volleyManager.getRequest(
                    url = url,
                    timeout = 1000,
                    needCache = true
                ).subscribe({ response ->
                    println("Response: $response")
                }, { error ->
                    println("Error: ${error.message}")
                })
            }
            println("FETCH_DATA_TIME: $time milliseconds")
        }
    }
}