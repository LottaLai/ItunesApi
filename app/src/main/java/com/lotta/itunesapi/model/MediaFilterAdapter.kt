package com.lotta.itunesapi.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lotta.itunesapi.R
import com.lotta.itunesapi.databinding.FilterListItemBinding

class MediaFilterAdapter : ListAdapter<FilterModel, MediaFilterAdapter.ViewHolder>(DiffCallback) {
    private lateinit var binding: FilterListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = FilterListItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.filter_list_item, parent, false)
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, currentList)
    }

    class ViewHolder(
        private val binding: FilterListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        var isClicked = false
        fun bind(item: FilterModel, currentList: MutableList<FilterModel>, ) {
            binding.apply {
                materialButton.text = item.kind
                isClicked = item.isClick

                materialButton.setOnClickListener {
                    currentList.forEach { _ -> isClicked = false }
                    isClicked = true
                }
            }
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<FilterModel>() {
        override fun areItemsTheSame(oldItem: FilterModel, newItem: FilterModel): Boolean {
            return oldItem.kind == newItem.kind
        }

        override fun areContentsTheSame(oldItem: FilterModel, newItem: FilterModel): Boolean {
            return oldItem.kind == newItem.kind
        }
    }
}