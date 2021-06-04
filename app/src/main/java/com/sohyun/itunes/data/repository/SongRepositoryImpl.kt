package com.sohyun.itunes.data.repository

import com.sohyun.itunes.data.model.SongResponse
import com.sohyun.itunes.data.network.ITunesApi
import com.sohyun.itunes.data.network.NetworkStatus
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val iTunesApi: ITunesApi
) : SongRepository, BaseRepository() {
    override suspend fun searchSong(term: String): NetworkStatus<List<SongResponse.Song>> =
        safeApiCall { iTunesApi.searchSong(term).results }
}