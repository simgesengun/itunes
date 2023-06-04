package com.simgesengun.itunes.domain.mapper.track

import com.simgesengun.itunes.data.datasource.locale.track.TrackEntity
import com.simgesengun.itunes.data.datasource.remote.model.Track
import com.simgesengun.itunes.domain.mapper.base.Mapper
import javax.inject.Inject

class TrackToTrackEntityMapper @Inject constructor(): Mapper<Track, TrackEntity> {
    override fun map(from: Track): TrackEntity = from.run {
        TrackEntity(
            trackId = trackId,
            artistName = artistName,
            collectionName = collectionName,
            trackName = trackName,
            previewUrl = previewUrl,
            artworkUrl100 = artworkUrl100,
            trackPrice = trackPrice,
            releaseDate = releaseDate,
            currency = currency
        )
    }
}