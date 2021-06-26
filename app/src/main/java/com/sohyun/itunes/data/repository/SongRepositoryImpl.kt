package com.sohyun.itunes.data.repository

import android.util.Log
import com.sohyun.itunes.data.local.SongLocalDataSource
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.data.network.ITunesApi
import com.sohyun.itunes.extension.mapToTrack
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
        private val iTunesApi: ITunesApi,
        private val songLocalDataSource: SongLocalDataSource
) : SongRepository, BaseRepository() {
    // FIXME isEmpty detect
    override suspend fun searchSong(term: String, offset: Int) {
        // FIXME 로컬 db 갯수 보고  없으면 서버 통신으로 가져오기
        val response = iTunesApi.searchSong(term, offset = offset)
        Log.d("TAG", "searchSong: response - $response")
//        response.mapToTrack().forEach {
//
//            if (songLocalDataSource.getFavoriteId()!!.contains(it.trackId)) {
//                it.isFavorite = true
//            }
//        }
        songLocalDataSource.insertTracks(response.mapToTrack())
    }

    override suspend fun getTrackList(): Flow<MutableList<Track>> = songLocalDataSource.getTrackList()

    override suspend fun getFavoriteList(): Flow<MutableList<Track>> = songLocalDataSource.getFavoriteList()

    override suspend fun getFavoriteId(): List<Int>? = songLocalDataSource.getFavoriteId()

    override suspend fun insertItem(track: Track) {
        songLocalDataSource.insertItem(track)
    }

    override suspend fun insertTracks(tracks: List<Track>) {
        songLocalDataSource.insertTracks(tracks)
    }

    override suspend fun updateTrack(track: Track) {
        songLocalDataSource.updateTrack(track)
    }

    override suspend fun deleteItemById(trackId: Int) {
        songLocalDataSource.deleteItemById(trackId)
    }
}