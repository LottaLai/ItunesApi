package com.lotta.itunesapi.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lotta.itunesapi.api.ApiContainer
import com.lotta.itunesapi.api.VolleyManager
import com.lotta.itunesapi.configuration.DataManager
import com.lotta.itunesapi.model.MediaModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.system.measureTimeMillis


class HomeViewModel @Inject constructor(
    private val dataManager: DataManager,
    private val volleyManager: VolleyManager
) : ViewModel() {
    var songList = MutableLiveData<MutableList<MediaModel>>()

    fun fetchAllDate(
        term: String = ""
    ) {
        viewModelScope.launch {
            val music = async {
                fetchData(
                    term = term,
                    media = "music",
                    limit = 20,
                    onSuccess = {
                        songList.value = it
                    })
            }
            awaitAll(music)
        }
    }

    fun fetchData(
        term: String = "",
        limit: Int = 0,
        media: String = "",
        offset: Int = 0,
        onSuccess: ((MutableList<MediaModel>) -> Unit)? = null,
        onFailure: ((String) -> Unit)? = null,
    ) {
        viewModelScope.launch {
            val urlBuilder = StringBuilder()
                .append(ApiContainer.endpoint())
                .append("search?")
                .append(if (term.isNotEmpty()) "&term=$term" else "")
                .append(if (media.isNotEmpty()) "&media=$media" else "")
                .append(if (limit > 0) "&limit=$limit" else "")
                .append(if (offset > 0) "&offset=$offset" else "")
            val url = urlBuilder.toString()
            val time = measureTimeMillis {
                volleyManager.getRequest(
                    url = url,
                    timeout = 1000,
                    needCache = true
                ).subscribe({ response ->
                    val jsonArray = response.getJSONArray("results")
                    val typeToken = object : TypeToken<MutableList<MediaModel>>() {}
                    val resultsList = Gson().fromJson(
                        jsonArray.toString(),
                        typeToken.type
                    ) as MutableList<MediaModel>
                    onSuccess?.invoke(resultsList)
                }, { error ->
                    val statusCode = (error as VolleyError).networkResponse.statusCode
                    println("Error: $error | StatusCode: $statusCode")
                    onFailure?.invoke(statusCode.toString())
                })
            }
            println("FETCH_DATA_TIME: $time milliseconds")
        }
    }
}