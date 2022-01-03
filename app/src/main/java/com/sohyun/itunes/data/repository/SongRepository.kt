package com.sohyun.itunes.data.repository

import com.sohyun.itunes.data.model.Track
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    suspend fun searchSong(term: String, offset: Int): List<Track>
    suspend fun getTrackList(): Flow<MutableList<Track>>
    suspend fun getFavoriteList(): Flow<MutableList<Track>>
    suspend fun getFavoriteId(): List<Int>?
    suspend fun insertItem(track: Track)
    suspend fun insertTracks(tracks: List<Track>)
    suspend fun updateTrack(isFavorite: Boolean, trackId: Int)
    suspend fun deleteItemById(trackId: Int)
    suspend fun clearTracks()
}