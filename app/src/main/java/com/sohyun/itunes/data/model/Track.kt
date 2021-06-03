package com.sohyun.itunes.data.model

data class Track(
    val trackName: String,
    val collectionName: String,
    val artistName: String,
    val artwork: String,
    var isFavorite: Boolean
)
