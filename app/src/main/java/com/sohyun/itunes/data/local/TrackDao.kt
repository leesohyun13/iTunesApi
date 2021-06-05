package com.sohyun.itunes.data.local

import androidx.room.*
import com.sohyun.itunes.data.model.Track
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Query("SELECT * FROM track")
    fun getFavoriteList(): Flow<MutableList<Track>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(track: Track)

    @Delete
    fun deleteItem(track: Track)
}