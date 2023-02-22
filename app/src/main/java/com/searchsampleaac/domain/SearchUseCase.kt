package com.searchsampleaac.domain

import androidx.paging.PagingData
import com.searchsampleaac.data.SearchRepository
import com.searchsampleaac.data.model.SearchItemModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) {
    operator fun invoke(query: String): Flow<PagingData<SearchItemModel>> {
        return repository.getSearchResultStream(query)
    }
}