package com.searchsampleaac.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.searchsampleaac.api.SearchService
import com.searchsampleaac.data.model.RepoItemModel
import com.searchsampleaac.data.SearchRepository.Companion.NETWORK_PAGE_SIZE

private const val STARTING_PAGE_INDEX = 1

class RepoPagingSource(
    private val service: SearchService,
    private val username: String
): PagingSource<Int, RepoItemModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoItemModel> {
        return try {
            val page = params.key ?: STARTING_PAGE_INDEX
            val response = service.getUserRepos(username, page)

            if (response.isSuccessful) {
                LoadResult.Page(
                    data = response.body() ?: emptyList(),
                    prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                    nextKey = if (response.body()?.size!! < NETWORK_PAGE_SIZE) null else page + 1
                )
            } else {
                LoadResult.Page(emptyList(), null, null)
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepoItemModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}