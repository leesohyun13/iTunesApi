package com.sohyun.itunes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.data.network.ITunesApi.Companion.DEFAULT_LIMIT
import com.sohyun.itunes.data.network.NetworkStatus
import com.sohyun.itunes.data.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val songRepository: SongRepository
): ViewModel() {
    private var page = 0
    private val isLoading = MutableLiveData(false)
    private val isError = MutableLiveData(false)
    private val trackList = MutableLiveData<MutableList<Track>>()
    private val favoriteList = MutableLiveData<MutableList<Track>>(mutableListOf())

    suspend fun searchSong() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val responseDeferred = async { songRepository.searchSong("greenday", page * DEFAULT_LIMIT) }
            val idsDeferred = async(Dispatchers.Default) { songRepository.getFavoriteId() }
            val ids = idsDeferred.await()
            val response = responseDeferred.await()
            when(response) {
                is NetworkStatus.Success -> {
                    val list = trackList.value ?: mutableListOf()
                    ids?.let {
                        response.data.forEach {
                            if (ids.contains(it.trackId)) it.isFavorite = true
                        }
                    }
                    list.addAll(response.data)
                    trackList.postValue(list)
                    isError.postValue(false)
                }
                is NetworkStatus.Failure -> isError.postValue(true)

            }
            isLoading.postValue(false)
        }
    }

    private suspend fun fetchFavoriteTrackList() {
        songRepository.getFavoriteList().collectLatest { tracks ->
            favoriteList.postValue(tracks)
        }
    }

    fun increasePage() {
        ++ page
    }

    fun onToggleFavorite(track: Track) {
        updateTrackItem(track)
        viewModelScope.launch(Dispatchers.Default) {
            songRepository.updateTrack(track)
        }
    }

    private fun updateTrackItem(track: Track) {
        trackList.value?.forEach {
            if (it.trackId == track.trackId) {
                it.isFavorite = track.isFavorite
                return@forEach
            }
        }
    }

    fun getFavoriteList(): LiveData<MutableList<Track>> = favoriteList
    fun getTrackList(): LiveData<MutableList<Track>> = trackList
    fun isLoading(): LiveData<Boolean> = isLoading
    fun isError(): LiveData<Boolean> = isError

    init {
        viewModelScope.launch { searchSong() }
        viewModelScope.launch(Dispatchers.Default) { fetchFavoriteTrackList() }
    }
}