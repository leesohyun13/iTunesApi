package com.sohyun.itunes.data.local

import com.sohyun.itunes.data.model.Track
import kotlinx.coroutines.flow.Flow

interface SongLocalDataSource {
    suspend fun getFavoriteList(): Flow<MutableList<Track>>
    suspend fun getFavoriteId(): Flow<List<Int>?>
    suspend fun insertItem(track: Track)
    suspend fun deleteItem(track: Track)
}