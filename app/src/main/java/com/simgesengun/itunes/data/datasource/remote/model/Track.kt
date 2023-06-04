package com.simgesengun.itunes.data.datasource.remote.model

import java.util.Date

data class Track(
    val trackId: Int?,
    val artistName: String?,
    val collectionName: String?,
    val trackName: String?,
    val previewUrl: String?,
    val artworkUrl100: String?,
    val trackPrice: String?,
    val releaseDate: Date?,
    val currency: String?
)