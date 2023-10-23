package com.lotta.itunesapi.ui.video.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lotta.itunesapi.R
import com.lotta.itunesapi.databinding.ItemMusicBinding
import com.lotta.itunesapi.databinding.ItemPromotionBinding
import com.lotta.itunesapi.databinding.ItemVideoBinding

class VideoRecyclerViewAdapter :
    ListAdapter<VideoRecyclerViewItem, RecyclerView.ViewHolder>(DiffCallback) {
    lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_music -> {
                MusicViewHolder(
                    ItemMusicBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            R.layout.item_promotion -> {
                PromotionViewHolder(
                    ItemPromotionBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            R.layout.item_video -> {
                VideoViewHolder(
                    ItemVideoBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }

            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position).itemClickListener = itemClickListener
        when (getItem(position)) {
            is VideoRecyclerViewItem.MusicItem -> {
                (holder as MusicViewHolder).bind(getItem(position) as VideoRecyclerViewItem.MusicItem)
            }

            is VideoRecyclerViewItem.PromotionItem -> {
                (holder as PromotionViewHolder).bind(getItem(position) as VideoRecyclerViewItem.PromotionItem)
            }

            is VideoRecyclerViewItem.VideoItem -> {
                (holder as VideoViewHolder).bind(getItem(position) as VideoRecyclerViewItem.VideoItem)
            }

            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is VideoRecyclerViewItem.MusicItem -> R.layout.item_music
            is VideoRecyclerViewItem.PromotionItem -> R.layout.item_promotion
            is VideoRecyclerViewItem.VideoItem -> R.layout.item_video
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, item: VideoRecyclerViewItem, position: Int)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<VideoRecyclerViewItem>() {
        override fun areItemsTheSame(
            oldItem: VideoRecyclerViewItem,
            newItem: VideoRecyclerViewItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: VideoRecyclerViewItem,
            newItem: VideoRecyclerViewItem
        ): Boolean {
            return oldItem == newItem
        }
    }

}