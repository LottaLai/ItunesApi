package com.lotta.itunesapi.ui.video.adapter

import androidx.recyclerview.widget.RecyclerView
import com.lotta.itunesapi.databinding.ItemMusicBinding
import com.lotta.itunesapi.ui.video.model.Music

class MusicViewHolder(
    private val binding: ItemMusicBinding
) : RecyclerView.ViewHolder(binding.root){
    private lateinit var _music : Music
    fun bind(item: VideoRecyclerViewItem.MusicItem) {
        _music = item.music
        binding.apply {
            textView.text = _music.id.toString()
            textView.setOnClickListener {
                item.itemClickListener.onItemClick(it, item, bindingAdapterPosition)
            }
        }
    }
}