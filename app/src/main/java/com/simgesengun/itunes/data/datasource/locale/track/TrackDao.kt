package com.simgesengun.itunes.data.datasource.locale.track

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.simgesengun.itunes.data.datasource.locale.DatabaseConstants.TRACK_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {

    @Query("SELECT * FROM $TRACK_TABLE")
    fun getAllAsFlow(): Flow<List<TrackEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<TrackEntity>?)

    @Query("SELECT * FROM $TRACK_TABLE WHERE trackId LIKE :id LIMIT 1")
    fun findById(id: Int): TrackEntity?

    @Query("DELETE FROM $TRACK_TABLE WHERE trackId LIKE :id")
    suspend fun deleteById(id: Int): Int
}