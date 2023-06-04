package com.simgesengun.itunes.data.di

import com.simgesengun.itunes.app.di.annotation.IODispatcher
import com.simgesengun.itunes.data.datasource.locale.track.TrackDao
import com.simgesengun.itunes.data.datasource.remote.ITunesApiDS
import com.simgesengun.itunes.data.repo.ITunesRepoImpl
import com.simgesengun.itunes.domain.mapper.track.TrackEntityToUIStateMapper
import com.simgesengun.itunes.domain.mapper.track.TrackToTrackEntityMapper
import com.simgesengun.itunes.domain.mapper.track.TrackToUIStateMapper
import com.simgesengun.itunes.domain.repo.ITunesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {
    @Provides
    @Singleton
    fun provideITunesRepo(
        iTunesApiDS: ITunesApiDS,
        trackDao: TrackDao,
        trackToTrackEntityMapper: TrackToTrackEntityMapper,
        trackEntityToUIStateMapper: TrackEntityToUIStateMapper,
        trackToUIStateMapper: TrackToUIStateMapper,
        @IODispatcher dispatcher: CoroutineDispatcher
    ): ITunesRepo = ITunesRepoImpl(
        iTunesApiDS = iTunesApiDS,
        trackDao = trackDao,
        trackToTrackEntityMapper = trackToTrackEntityMapper,
        trackEntityToUIStateMapper = trackEntityToUIStateMapper,
        trackToUIStateMapper = trackToUIStateMapper,
        dispatcher = dispatcher
    )
}