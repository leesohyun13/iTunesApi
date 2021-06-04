package com.sohyun.itunes.data.local

import com.sohyun.itunes.data.model.Track
import kotlinx.coroutines.flow.Flow

interface SongLocalDataSource {
    fun getAll(): List<Track>
    fun getFavoriteList(): Flow<List<Track>>
    fun insertItem(track: Track)
    fun deleteItem(track: Track)
}