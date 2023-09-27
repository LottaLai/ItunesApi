package com.lotta.itunesapi.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lotta.itunesapi.configuration.DataManager
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val dataManager: DataManager
) : ViewModel() {
}