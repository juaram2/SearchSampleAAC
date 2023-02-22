package com.searchsampleaac.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.searchsampleaac.data.model.SearchItemModel
import com.searchsampleaac.databinding.ItemUserBinding

class SearchViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: SearchItemModel) {
        binding.item = item
        binding.executePendingBindings()
    }
}