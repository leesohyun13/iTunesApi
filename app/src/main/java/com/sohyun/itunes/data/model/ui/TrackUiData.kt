package com.sohyun.itunes.data.model.ui

import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.Track

sealed class TrackUiData(val layoutRes: Int) {
    class Title(val title: String) : TrackUiData(R.layout.cell_top)
    class Content(val tracks: List<Track>) : TrackUiData(R.layout.cell_content)
    object Bottom : TrackUiData(R.layout.cell_bottom)
    object Empty : TrackUiData(R.layout.cell_empty)
}