package com.sohyun.itunes.ui.adapter

import com.sohyun.itunes.data.model.Track

interface TrackItemListener {
    fun onClickTrackItem(track: Track)
}