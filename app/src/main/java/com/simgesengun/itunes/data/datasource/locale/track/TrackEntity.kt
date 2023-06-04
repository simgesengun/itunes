package com.simgesengun.itunes.data.datasource.locale.track

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.simgesengun.itunes.data.datasource.locale.DatabaseConstants.TRACK_TABLE
import java.util.Date

@Entity(tableName = TRACK_TABLE)
data class TrackEntity(
    @PrimaryKey
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