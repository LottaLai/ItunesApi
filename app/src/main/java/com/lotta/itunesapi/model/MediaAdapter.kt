package com.lotta.itunesapi.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lotta.itunesapi.R
import com.lotta.itunesapi.databinding.MediaListItemBinding

class MediaAdapter : ListAdapter<Track, MediaAdapter.ViewHolder>(DiffCallback) {
    private lateinit var binding: MediaListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MediaListItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.media_list_item, parent, false)
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        if(holder.isUntie) {
            holder.bind(getItem(holder.adapterPosition))
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        if(!holder.isUntie) {
            holder.untie()
        }
    }

    class ViewHolder(
        private val binding: MediaListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        var isUntie = false
        fun bind(item: Track) {
            binding.apply {
                songNameTextView.text = item.trackName
                artistTextView.text = item.artistName
                Glide.with(root).load(item.artworkUrl100).override(100, 100)
                    .into(shapeableImageView)
                isUntie = false
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


    companion object DiffCallback : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.collectionId == newItem.collectionId
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.collectionId == newItem.collectionId
        }
    }
}
