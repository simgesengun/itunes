package com.simgesengun.itunes.domain.repo

import androidx.paging.PagingData
import com.simgesengun.itunes.data.datasource.remote.model.ResponseSearch
import com.simgesengun.itunes.data.datasource.remote.model.Track
import com.simgesengun.itunes.network.model.NetworkResponse
import com.simgesengun.itunes.ui.uiState.TrackUIState
import kotlinx.coroutines.flow.Flow

interface ITunesRepo {
    suspend fun search(): NetworkResponse<ResponseSearch, Any>

    fun getTrackUIStatePagingData(): Flow<PagingData<TrackUIState>>

    fun getTrackListAsFlow(): Flow<List<TrackUIState>>

    suspend fun saveTrackList(tracks: List<Track>?)

    fun getTrackById(id: Int): Flow<TrackUIState?>

    suspend fun deleteTrackById(id: Int)
}