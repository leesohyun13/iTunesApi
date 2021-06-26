package com.sohyun.itunes.data.local

import androidx.room.*
import com.sohyun.itunes.data.model.Track
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTracks(tracks: List<Track>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(track: Track)

    @Query("SELECT * FROM track")
    fun getTrackList(): Flow<MutableList<Track>>

    @Query("SELECT * FROM track WHERE isFavorite = :isFavorite")
    fun getFavoriteList(isFavorite: Boolean = true): Flow<MutableList<Track>>

    @Query("SELECT trackId FROM track WHERE isFavorite = :isFavorite")
    fun getFavoriteId(isFavorite: Boolean = true): List<Int>?

    @Query("DELETE FROM track WHERE trackId = :trackId")
    fun deleteItemById(trackId: Int)

    @Update
    suspend fun updateTrack(track: Track)

    @Delete
    fun deleteItem(track: Track)
}