package com.sohyun.itunes.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sohyun.itunes.data.model.Track
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Query("SELECT * FROM track")
    fun getAll(): List<Track>

    @Query("SELECT * FROM track WHERE isFavorite == 1") // true = 1
    fun getLikeList(): Flow<List<Track>> // 테이블의 데이터가 변경될 때마다 전체 결과 세트를 다시 내보냄

    @Insert
    fun insertItem(track: Track)

    @Delete
    fun deleteItem(track: Track)
}