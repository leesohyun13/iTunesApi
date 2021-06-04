package com.sohyun.itunes.data.repository

import com.sohyun.itunes.data.model.SongResponse
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.data.network.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    suspend fun searchSong(term: String): NetworkStatus<List<SongResponse.Song>>
    fun getFavoriteList(): Flow<List<Track>>
    fun insertItem(track: Track)
    fun deleteItem(track: Track)
}