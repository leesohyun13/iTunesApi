package com.sohyun.itunes.data.repository

import com.sohyun.itunes.data.model.Song
import com.sohyun.itunes.data.network.NetworkStatus

interface SongRepository {
    suspend fun searchSong(term: String): NetworkStatus<List<Song>>
}