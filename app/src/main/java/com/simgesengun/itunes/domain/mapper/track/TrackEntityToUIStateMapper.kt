package com.simgesengun.itunes.domain.mapper.track

import com.simgesengun.itunes.data.datasource.locale.track.TrackEntity
import com.simgesengun.itunes.domain.mapper.base.Mapper
import com.simgesengun.itunes.domain.usecase.FormatDateUseCase
import com.simgesengun.itunes.ui.uiState.TrackUIState
import javax.inject.Inject

class TrackEntityToUIStateMapper @Inject constructor(
    private val trackPriceMapper: TrackPriceMapper,
    private val formatDateUseCase: FormatDateUseCase
): Mapper<TrackEntity, TrackUIState> {
    override fun map(from: TrackEntity): TrackUIState = from.run {
        TrackUIState(
            trackId = trackId ?: 0,
            artistName = artistName.orEmpty(),
            collectionName = collectionName.orEmpty(),
            trackName = trackName.orEmpty(),
            previewUrl = previewUrl.orEmpty(),
            artworkUrl100 = artworkUrl100.orEmpty(),
            trackPrice = trackPriceMapper.map(trackPrice to currency),
            releaseDate = formatDateUseCase(releaseDate).orEmpty()
        )
    }
}