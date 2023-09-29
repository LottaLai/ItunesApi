package com.lotta.itunesapi.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.lotta.itunesapi.configuration.DataManager
import com.lotta.itunesapi.model.FilterModel
import com.lotta.itunesapi.model.MediaModel
import com.lotta.itunesapi.model.MediaRepo
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val dataManager: DataManager,
    private val repo: MediaRepo
) : ViewModel() {
    var originList: MutableList<MediaModel> = mutableListOf()
    var songList = MutableLiveData<MutableList<MediaModel>>()
    var mediaFilterList = MutableLiveData<MutableList<FilterModel>>()
    var countryFilterList = MutableLiveData<MutableList<FilterModel>>()

    val paging = repo.pager.liveData.cachedIn(viewModelScope)

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
                        originList.plusAssign(it)
                        songList.value = it

                        val mediaTypeList = arrayListOf(FilterModel("ALL", true))
                        it.map { model -> model.kind }
                            .distinct()
                            .toMutableList()
                            .forEach { name ->
                                mediaTypeList.add(FilterModel(name, false))
                            }
                        mediaFilterList.value = mediaTypeList

                        val countryList = arrayListOf(FilterModel("ALL", true))
                        it.map { model -> model.country }
                            .distinct()
                            .toMutableList()
                            .forEach { name ->
                                countryList.add(FilterModel(name, false))
                            }
                        countryFilterList.value = countryList
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
//        viewModelScope.launch {
//            val urlBuilder = StringBuilder()
//                .append(ApiContainer.endpoint())
//                .append("search?")
//                .append(if (term.isNotEmpty()) "&term=$term" else "")
//                .append(if (media.isNotEmpty()) "&media=$media" else "")
//                .append(if (limit > 0) "&limit=$limit" else "")
//                .append(if (offset > 0) "&offset=$offset" else "")
//            val url = urlBuilder.toString()
//            val time = measureTimeMillis {
//                volleyManager.getRequest(
//                    url = url,
//                    timeout = 1000,
//                    needCache = true
//                ).subscribe({ response ->
//                    val jsonArray = response.getJSONArray("results")
//                    val typeToken = object : TypeToken<MutableList<MediaModel>>() {}
//                    val resultsList = Gson().fromJson(
//                        jsonArray.toString(),
//                        typeToken.type
//                    ) as MutableList<MediaModel>
//                    onSuccess?.invoke(resultsList)
//                }, { error ->
//                    val statusCode = (error as VolleyError).networkResponse.statusCode
//                    println("Error: $error | StatusCode: $statusCode")
//                    onFailure?.invoke(statusCode.toString())
//                })
//            }
//            println("FETCH_DATA_TIME: $time milliseconds")
//        }
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