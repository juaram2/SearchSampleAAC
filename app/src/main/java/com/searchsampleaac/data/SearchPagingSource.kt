package com.searchsampleaac.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.searchsampleaac.data.api.SearchService
import com.searchsampleaac.data.SearchRepositoryImpl.Companion.NETWORK_PAGE_SIZE
import com.searchsampleaac.data.model.SearchItemModel

private const val STARTING_PAGE_INDEX = 1

class SearchPagingSource(
    private val service: SearchService,
    private val query: String
): PagingSource<Int, SearchItemModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchItemModel> {
        return try {
            val page = params.key ?: STARTING_PAGE_INDEX
            val response = service.searchUser(query, page)

            if (response.isSuccessful) {
                LoadResult.Page(
                    data = response.body()?.items ?: emptyList(),
                    prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                    nextKey = if (page >= response.body()?.totalCount?.div(NETWORK_PAGE_SIZE)!!) null else page + 1
                )
            } else {
                LoadResult.Page(emptyList(), null, null)
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchItemModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}