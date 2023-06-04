package com.simgesengun.itunes.data.datasource.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.simgesengun.itunes.data.datasource.locale.track.TrackDao
import com.simgesengun.itunes.data.datasource.locale.track.TrackEntity

@Database(
    entities = [TrackEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDB : RoomDatabase() {
    abstract fun trackDao(): TrackDao
}