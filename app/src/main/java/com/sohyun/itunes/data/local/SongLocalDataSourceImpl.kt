package com.sohyun.itunes.data.local

import com.sohyun.itunes.data.model.Track
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SongLocalDataSourceImpl @Inject constructor(
    private val trackDao: TrackDao
) : SongLocalDataSource {
    override suspend fun getFavoriteList(): Flow<MutableList<Track>> = trackDao.getFavoriteList()
    override suspend fun getFavoriteId(): Flow<List<Int>?> = trackDao.getFavoriteId()
    override suspend fun insertItem(track: Track) = trackDao.insertItem(track)
    override suspend fun deleteItemById(trackId: Int) = trackDao.deleteItemById(trackId)
}