package com.searchsampleaac.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.searchsampleaac.data.model.SearchItemModel
import com.searchsampleaac.databinding.ItemUserBinding

class SearchAdapter : PagingDataAdapter<SearchItemModel, SearchViewHolder>(UserComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val items = getItem(position)
        items?.let {
            holder.bind(it)
        }
    }

    companion object UserComparator : DiffUtil.ItemCallback<SearchItemModel>() {
        override fun areItemsTheSame(oldItem: SearchItemModel, newItem: SearchItemModel): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchItemModel,
            newItem: SearchItemModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}