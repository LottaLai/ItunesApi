package com.lotta.itunesapi.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lotta.itunesapi.R
import com.lotta.itunesapi.databinding.FilterListItemBinding
import com.lotta.itunesapi.model.FilterModel

class FilterAdapter(
    private val onButtonClickListener: OnButtonClickListener
    ) : ListAdapter<FilterModel, FilterAdapter.ViewHolder>(DiffCallback) {
    private lateinit var binding: FilterListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = FilterListItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.filter_list_item, parent, false)
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.button.setOnClickListener{
            if(!item.isClicked) {
                currentList.forEach { filterModel -> filterModel.isClicked = false }
                item.isClicked = true
                notifyDataSetChanged()
            }
            onButtonClickListener.onButtonClick(item.key)
        }
    }

    interface OnButtonClickListener{
        fun onButtonClick(item: String)
    }

    class ViewHolder(
        private val binding: FilterListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val button = binding.materialButton
        fun bind(item: FilterModel) {
            binding.apply {
                materialButton.text = item.value
                materialButton.setBackgroundColor(if(item.isClicked) Color.GREEN else Color.GRAY)
                materialButton.isEnabled = !item.isClicked
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<FilterModel>() {
        override fun areItemsTheSame(oldItem: FilterModel, newItem: FilterModel): Boolean {
            return oldItem.value == newItem.value
        }

        override fun areContentsTheSame(oldItem: FilterModel, newItem: FilterModel): Boolean {
            return oldItem.value == newItem.value
        }
    }
}