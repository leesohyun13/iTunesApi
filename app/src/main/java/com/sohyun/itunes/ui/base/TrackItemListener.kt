package com.sohyun.itunes.ui.base

import com.sohyun.itunes.data.model.Track

interface TrackItemListener {
    fun onClickTrackItem(isFavorite: Boolean, track: Track)
}