package com.sohyun.itunes.data.repository

import com.sohyun.itunes.data.local.SongLocalDataSource
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.data.network.ITunesApi
import com.sohyun.itunes.extension.mapToTrack
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
        private val iTunesApi: ITunesApi,
        private val songLocalDataSource: SongLocalDataSource
) : SongRepository {
    override suspend fun searchSong(term: String, offset: Int): List<Track> =
        iTunesApi.searchSong(term, offset = offset).mapToTrack()

    override suspend fun getTrackList(): Flow<MutableList<Track>> = songLocalDataSource.getTrackList()
    override suspend fun getFavoriteList(): Flow<MutableList<Track>> = songLocalDataSource.getFavoriteList()
    override suspend fun getFavoriteId(): List<Int>? = songLocalDataSource.getFavoriteId()
    override suspend fun insertItem(track: Track) { songLocalDataSource.insertItem(track) }
    override suspend fun insertTracks(tracks: List<Track>) = songLocalDataSource.insertTracks(tracks)
    override suspend fun updateTrack(isFavorite: Boolean, trackId: Int) = songLocalDataSource.updateTrack(isFavorite, trackId)
    override suspend fun deleteItemById(trackId: Int) = songLocalDataSource.deleteItemById(trackId)
    override suspend fun clearTracks() = songLocalDataSource.clearTracks()
}