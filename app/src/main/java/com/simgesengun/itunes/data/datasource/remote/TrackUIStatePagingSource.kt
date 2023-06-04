package com.simgesengun.itunes.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.simgesengun.itunes.data.datasource.remote.TrackPagingSourceConstants.INITIAL_KEY
import com.simgesengun.itunes.data.datasource.remote.TrackPagingSourceConstants.NETWORK_PAGE_SIZE
import com.simgesengun.itunes.data.datasource.remote.model.Track
import com.simgesengun.itunes.domain.mapper.track.TrackToUIStateMapper
import com.simgesengun.itunes.network.model.NetworkResponse
import com.simgesengun.itunes.ui.uiState.TrackUIState

class TrackUIStatePagingSource(
    private val term: String,
    private val iTunesApiDS: ITunesApiDS,
    private val trackToUIStateMapper: TrackToUIStateMapper
) : PagingSource<Int, TrackUIState>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrackUIState> {
        val position = params.key ?: INITIAL_KEY
        val offset = if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 1 else INITIAL_KEY
        return try {
            val response = iTunesApiDS.searchWithPagination(
                term = term,
                offset = offset,
                limit = params.loadSize
            )
            when(response) {
                is NetworkResponse.ApiError -> LoadResult.Error(Throwable())
                is NetworkResponse.NetworkError -> {
                    LoadResult.Error(response.exception ?: Throwable())
                }
                is NetworkResponse.Success -> {
                    response.body.results.let { trackList ->
                        val nextKey = when(trackList) {
                            null, emptyList<Track>() -> null
                            else -> position + (params.loadSize / NETWORK_PAGE_SIZE)
                        }
                        LoadResult.Page(
                            data = trackList?.map { trackToUIStateMapper.map(it) }.orEmpty(),
                            prevKey = null,
                            nextKey = nextKey
                        )
                    }
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TrackUIState>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}