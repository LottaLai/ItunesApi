package com.lotta.itunesapi.ui.video.adapter

import androidx.recyclerview.widget.RecyclerView
import com.lotta.itunesapi.databinding.ItemPromotionBinding
import com.lotta.itunesapi.ui.video.model.Promotion

class PromotionViewHolder(
    private val binding: ItemPromotionBinding
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var _promotion : Promotion
    fun bind(item: VideoRecyclerViewItem.PromotionItem) {
        _promotion = item.promotion
    }
}