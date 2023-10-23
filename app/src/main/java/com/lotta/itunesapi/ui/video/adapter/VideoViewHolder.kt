package com.lotta.itunesapi.ui.video.adapter

import androidx.recyclerview.widget.RecyclerView
import com.lotta.itunesapi.databinding.ItemVideoBinding
import com.lotta.itunesapi.ui.video.model.Video

class VideoViewHolder(
    private val binding: ItemVideoBinding
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var _video : Video
    fun bind(item: VideoRecyclerViewItem.VideoItem) {
        _video = item.video
        binding.apply {

        }
    }
}