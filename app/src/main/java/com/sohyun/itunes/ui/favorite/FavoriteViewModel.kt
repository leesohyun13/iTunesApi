package com.sohyun.itunes.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sohyun.itunes.backgroundNotifyObserver
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.data.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val songRepository: SongRepository
): ViewModel() {
    private val trackList = MutableLiveData<MutableList<Track>>().apply {
        value = arrayListOf()
    }

    suspend fun getFavoriteTrackList() {
        songRepository.getFavoriteList().collect { tracks ->
            trackList.value?.clear()
            trackList.value?.addAll(tracks)
            trackList.backgroundNotifyObserver()
        }
    }

    suspend fun deleteFavoriteTrack(track: Track) {
        songRepository.deleteItem(track)
    }

    fun getTrackList(): LiveData<MutableList<Track>> = trackList
}