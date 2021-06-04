package com.sohyun.itunes.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sohyun.itunes.data.model.Track
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Query("SELECT * FROM track")
    fun getAll(): List<Track>

    @Query("SELECT * FROM track WHERE isFavorite == 1") // true = 1
    fun getFavoriteList(): Flow<List<Track>>

    @Insert
    fun insertItem(track: Track)

    @Delete
    fun deleteItem(track: Track)
}