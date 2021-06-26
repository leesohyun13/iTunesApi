package com.sohyun.itunes.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "track")
data class Track(
    @PrimaryKey
    @ColumnInfo(name = "trackId") val trackId: Int,
    @ColumnInfo(name = "trackName") val trackName: String,
    @ColumnInfo(name = "collectionName") val collectionName: String,
    @ColumnInfo(name = "artworkUrl60")val artworkUrl60: String,
    @ColumnInfo(name = "artistName") val artistName: String,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean
)
