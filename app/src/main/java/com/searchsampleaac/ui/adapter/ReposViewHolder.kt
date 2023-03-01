package com.searchsampleaac.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.searchsampleaac.data.model.RepoItemModel
import com.searchsampleaac.databinding.ItemRepoBinding

class ReposViewHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: RepoItemModel) {
        binding.item = item
        binding.executePendingBindings()
    }
}