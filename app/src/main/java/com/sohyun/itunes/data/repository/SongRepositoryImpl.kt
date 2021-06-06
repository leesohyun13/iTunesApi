package com.sohyun.itunes.data.repository

import com.sohyun.itunes.data.local.SongLocalDataSource
import com.sohyun.itunes.data.model.SongResponse
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.data.network.ITunesApi
import com.sohyun.itunes.data.network.NetworkStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
        private val iTunesApi: ITunesApi,
        private val songLocalDataSource: SongLocalDataSource
) : SongRepository, BaseRepository() {
    override suspend fun searchSong(term: String,offset: Int): NetworkStatus<List<SongResponse.Song>?> =
            safeApiCall { iTunesApi.searchSong(term, offset = offset).results }

    override suspend fun getFavoriteList(): Flow<MutableList<Track>> = songLocalDataSource.getFavoriteList()

    override suspend fun getFavoriteId(): List<Int>? = songLocalDataSource.getFavoriteId()

    override suspend fun insertItem(track: Track) {
        songLocalDataSource.insertItem(track)
    }

    override suspend fun deleteItem(track: Track) {
        songLocalDataSource.deleteItem(track)
    }
}