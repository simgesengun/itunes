package com.simgesengun.itunes.ui.uiState

data class TrackUIState(
    val trackId: Int,
    val artistName: String,
    val collectionName: String?,
    val trackName: String,
    val previewUrl: String,
    val artworkUrl100: String,
    val trackPrice: String?,
    val releaseDate: String
)