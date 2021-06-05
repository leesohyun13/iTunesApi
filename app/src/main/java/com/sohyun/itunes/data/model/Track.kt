package com.sohyun.itunes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "track")
data class Track(
    @PrimaryKey val trackId: Int,
    val trackName: String,
    val collectionName: String,
    val artworkUrl60: String,
    val artistName: String,
    val artwork: String,
    var isFavorite: Boolean
)
