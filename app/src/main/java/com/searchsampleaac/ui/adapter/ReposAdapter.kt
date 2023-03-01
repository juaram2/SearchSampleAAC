package com.searchsampleaac.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.searchsampleaac.data.model.RepoItemModel
import com.searchsampleaac.databinding.ItemRepoBinding

class ReposAdapter : PagingDataAdapter<RepoItemModel, ReposViewHolder>(RepoComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        return ReposViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        val items = getItem(position)
        items?.let {
            holder.bind(it)
        }
    }

    companion object RepoComparator : DiffUtil.ItemCallback<RepoItemModel>() {
        override fun areItemsTheSame(oldItem: RepoItemModel, newItem: RepoItemModel): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RepoItemModel,
            newItem: RepoItemModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}