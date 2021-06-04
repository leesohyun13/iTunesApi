package com.sohyun.itunes.data.repository

import com.sohyun.itunes.data.model.SongResponse
import com.sohyun.itunes.data.network.NetworkStatus

interface SongRepository {
    suspend fun searchSong(term: String): NetworkStatus<List<SongResponse.Song>>
}