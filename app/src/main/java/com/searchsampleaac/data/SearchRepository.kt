package com.searchsampleaac.data

import androidx.paging.PagingData
import com.searchsampleaac.data.model.RepoItemModel
import com.searchsampleaac.data.model.SearchItemModel
import kotlinx.coroutines.flow.Flow


interface SearchRepository {
    fun getSearchResultStream(query: String): Flow<PagingData<SearchItemModel>>

    fun getUserRepoStream(username: String): Flow<PagingData<RepoItemModel>>
}