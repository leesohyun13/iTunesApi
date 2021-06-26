package com.sohyun.itunes.extension

import com.sohyun.itunes.data.model.SongResponse
import com.sohyun.itunes.data.model.Track

fun SongResponse.mapToTrack(): List<Track> {
    return this.results.map { item ->
        Track(item.trackId, item.trackName, item.collectionName, item.artworkUrl60, item.artistName, item.isFavorite)
    }
}