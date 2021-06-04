package com.sohyun.itunes.data.repository

import com.sohyun.itunes.data.local.SongLocalDataSource
import com.sohyun.itunes.data.model.SongResponse
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.data.network.ITunesApi
import com.sohyun.itunes.data.network.NetworkStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val iTunesApi: ITunesApi,
    private val songLocalDataSource: SongLocalDataSource
) : SongRepository, BaseRepository() {
    override suspend fun searchSong(term: String): NetworkStatus<List<SongResponse.Song>> =
        safeApiCall { iTunesApi.searchSong(term).results }

    override fun getFavoriteList(): Flow<List<Track>> = flow {
        songLocalDataSource.getFavoriteList()
    }

    override fun insertItem(track: Track) {
        songLocalDataSource.insertItem(track)
    }

    override fun deleteItem(track: Track) {
        songLocalDataSource.deleteItem(track)
    }
}