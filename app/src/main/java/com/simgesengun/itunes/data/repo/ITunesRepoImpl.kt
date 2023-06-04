package com.simgesengun.itunes.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.simgesengun.itunes.app.di.annotation.IODispatcher
import com.simgesengun.itunes.data.datasource.locale.track.TrackDao
import com.simgesengun.itunes.data.datasource.remote.ITunesApiDS
import com.simgesengun.itunes.data.datasource.remote.TrackPagingSourceConstants.NETWORK_PAGE_SIZE
import com.simgesengun.itunes.data.datasource.remote.TrackUIStatePagingSource
import com.simgesengun.itunes.data.datasource.remote.model.ResponseSearch
import com.simgesengun.itunes.data.datasource.remote.model.Track
import com.simgesengun.itunes.domain.mapper.track.TrackEntityToUIStateMapper
import com.simgesengun.itunes.domain.mapper.track.TrackToTrackEntityMapper
import com.simgesengun.itunes.domain.mapper.track.TrackToUIStateMapper
import com.simgesengun.itunes.domain.repo.ITunesRepo
import com.simgesengun.itunes.network.model.NetworkResponse
import com.simgesengun.itunes.ui.uiState.TrackUIState
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class ITunesRepoImpl @Inject constructor(
    private val iTunesApiDS: ITunesApiDS,
    private val trackDao: TrackDao,
    private val trackToTrackEntityMapper: TrackToTrackEntityMapper,
    private val trackEntityToUIStateMapper: TrackEntityToUIStateMapper,
    private val trackToUIStateMapper: TrackToUIStateMapper,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : ITunesRepo {

    override suspend fun search(): NetworkResponse<ResponseSearch, Any> {
       iTunesApiDS.search(term).run {
            if (this is NetworkResponse.Success) {
                saveTrackList(body.results)
            }
           return this
        }
    }

    override fun getTrackUIStatePagingData(): Flow<PagingData<TrackUIState>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                TrackUIStatePagingSource(
                    term = term,
                    iTunesApiDS = iTunesApiDS,
                    trackToUIStateMapper = trackToUIStateMapper
                )
            }
        ).flow
    }

    override fun getTrackListAsFlow(): Flow<List<TrackUIState>> {
        return trackDao.getAllAsFlow().map { list ->
            list.map { trackEntityToUIStateMapper.map(it) }
        }
    }

    override suspend fun saveTrackList(tracks: List<Track>?) {
        trackDao.insertAll(
            tracks?.map {
                trackToTrackEntityMapper.map(it)
            }
        )
    }

    override fun getTrackById(id: Int): Flow<TrackUIState?> {
        return flow {
            emit(
                trackDao.findById(id)?.let {
                    trackEntityToUIStateMapper.map(it)
                }
            )
        }.flowOn(dispatcher)
    }

    override suspend fun deleteTrackById(id: Int) {
        trackDao.deleteById(id)
    }

    companion object {
        const val term = "jack+johnson"
    }
}