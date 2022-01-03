package com.sohyun.itunes.model

import com.sohyun.itunes.data.model.Track

/**
 * iTunes
 * Class: TrackUiModel
 * Created by leesohyun on 2022/01/02.
 *
 * Description:
 */
sealed class TrackUiModel {
    class Content(val track: Track): TrackUiModel()
}
