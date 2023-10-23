package com.lotta.itunesapi.ui.video.adapter

import com.lotta.itunesapi.ui.video.model.Music
import com.lotta.itunesapi.ui.video.model.Promotion
import com.lotta.itunesapi.ui.video.model.Video

sealed class VideoRecyclerViewItem {
    lateinit var itemClickListener: VideoRecyclerViewAdapter.OnItemClickListener
    data class MusicItem(val music: Music) : VideoRecyclerViewItem()
    data class VideoItem(val video: Video) : VideoRecyclerViewItem()
    data class PromotionItem(val promotion: Promotion) : VideoRecyclerViewItem()
}