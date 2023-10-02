package com.lotta.itunesapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lotta.itunesapi.R
import com.lotta.itunesapi.databinding.MediaListItemBinding
import com.lotta.itunesapi.room.Track

class TrackAdapter(
    private val onItemClickListener: OnItemClickListener
) : PagingDataAdapter<Track, TrackAdapter.TrackViewHolder>(DiffCallback) {
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

    override fun onViewAttachedToWindow(holder: TrackViewHolder) {
        super.onViewAttachedToWindow(holder)
        if(holder.isUntie) {
            holder.bind(getItem(holder.adapterPosition))
        }
    }

    override fun onViewDetachedFromWindow(holder: TrackViewHolder) {
        super.onViewDetachedFromWindow(holder)
        if(!holder.isUntie) {
            holder.untie()
        }
    }

    inner class TrackViewHolder(
        private val binding: MediaListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        var isUntie = false

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    item?.let {
                        onItemClickListener.onItemClick(it)
                    }
                }
            }
        }

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

        fun untie() {
            binding.apply {
                songNameTextView.text = ""
                artistTextView.text = ""
                Glide.with(root).clear(shapeableImageView)
                isUntie = true
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(track: Track)
    }

    object DiffCallback : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.trackName == newItem.trackName
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem == newItem
        }
    }

}
