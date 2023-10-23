package com.lotta.itunesapi.ui.video.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lotta.itunesapi.ui.video.adapter.VideoRecyclerViewItem
import com.lotta.itunesapi.ui.video.model.Music
import com.lotta.itunesapi.ui.video.model.Video
import com.lotta.itunesapi.ui.video.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val videoRepository: VideoRepository
) : ViewModel() {
    private val _videoListItemsLiveData = MutableLiveData<List<VideoRecyclerViewItem>>()
    val videoListItemsLiveData: LiveData<List<VideoRecyclerViewItem>>
        get() = _videoListItemsLiveData

    init {
        getVideoListItems()
    }

    private fun getVideoListItems() = viewModelScope.launch {
        val videoItemsList = mutableListOf<VideoRecyclerViewItem>()
        videoItemsList.add(VideoRecyclerViewItem.VideoItem(Video(1)))
        videoItemsList.add(VideoRecyclerViewItem.VideoItem(Video(1)))
        videoItemsList.add(VideoRecyclerViewItem.MusicItem(Music(1)))
        videoItemsList.shuffle()
        _videoListItemsLiveData.postValue(videoItemsList)
    }
}