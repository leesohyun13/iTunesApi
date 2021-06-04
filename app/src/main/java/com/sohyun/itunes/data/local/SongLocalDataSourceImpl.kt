package com.sohyun.itunes.data.local

import com.sohyun.itunes.data.model.Track
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SongLocalDataSourceImpl @Inject constructor(
    private val trackDao: TrackDao
) : SongLocalDataSource {
    override fun getAll(): List<Track> = trackDao.getAll()

    override fun getFavoriteList(): Flow<List<Track>> = trackDao.getFavoriteList()

    override fun insertItem(track: Track) {
        trackDao.insertItem(track)
    }

    override fun deleteItem(track: Track) {
        trackDao.deleteItem(track)
    }
}