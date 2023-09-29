package com.lotta.itunesapi.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lotta.itunesapi.R
import com.lotta.itunesapi.databinding.MediaListItemBinding

class TrackAdapter : PagingDataAdapter<Track, TrackAdapter.TrackViewHolder>(TrackComparator) {
    private lateinit var binding: MediaListItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        binding = MediaListItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.media_list_item, parent, false)
        )
        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = getItem(position)
        holder.bind(track)
    }


    inner class TrackViewHolder(
        private val binding: MediaListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(track: Track?) {
            track?.let {
                binding.apply {
                    songNameTextView.text = track.trackName
                    artistTextView.text = track.artistName
                    Glide.with(root).load(track.artworkUrl100).override(100, 100)
                        .into(shapeableImageView)
                }
            }
        }
    }

    object TrackComparator : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.trackName == newItem.trackName
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem == newItem
        }
    }
}
