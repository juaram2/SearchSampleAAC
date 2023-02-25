package com.searchsampleaac.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.searchsampleaac.data.model.SearchItemModel
import com.searchsampleaac.databinding.ItemUserBinding

class SearchViewHolder(private val binding: ItemUserBinding, private val listener: OnUserClickListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: SearchItemModel) {
        binding.item = item
        binding.onUserClick = View.OnClickListener {
            listener.userClick(item.login)
        }
        binding.executePendingBindings()
    }
}