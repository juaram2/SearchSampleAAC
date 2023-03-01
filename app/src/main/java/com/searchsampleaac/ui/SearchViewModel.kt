package com.searchsampleaac.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.searchsampleaac.data.SearchRepository
import com.searchsampleaac.data.model.RepoItemModel
import com.searchsampleaac.data.model.SearchItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
): ViewModel() {

    fun search(charSequence: CharSequence): Flow<PagingData<SearchItemModel>> {
        val query = charSequence.toString()
        return repository.getSearchResultStream(query).cachedIn(viewModelScope)
    }

    fun getRepo(username: String): Flow<PagingData<RepoItemModel>> {
        return repository.getUserRepoStream(username).cachedIn(viewModelScope)
    }
}