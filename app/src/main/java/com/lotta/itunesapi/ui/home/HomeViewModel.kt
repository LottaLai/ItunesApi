package com.lotta.itunesapi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lotta.itunesapi.configuration.DataManager
import com.lotta.itunesapi.configuration.VolleyManager
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val dataManager: DataManager,
    private val volleyManager: VolleyManager
) : ViewModel() {

    fun getData(){
        volleyManager.getRequest("https://itunes.apple.com/search?term=jack+johnson&limit=25")
    }
}