package com.sohyun.itunes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.data.network.ITunesApi.Companion.DEFAULT_LIMIT
import com.sohyun.itunes.data.page.TrackPagingSource
import com.sohyun.itunes.data.page.TrackPagingSource.Companion.CONTENTS_COUNT
import com.sohyun.itunes.data.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val songRepository: SongRepository
): ViewModel() {
    init {
        viewModelScope.launch {
            async(Dispatchers.Default) { songRepository.clearTracks() }.await()
            searchSong()
        }
        viewModelScope.launch(Dispatchers.Default) { fetchFavoriteTrackList() }
        viewModelScope.launch(Dispatchers.Default) {
            songRepository.getTrackList().collectLatest {
                trackList.postValue(it.toMutableList())
            }
        }
    }

    private var page = 0
    private val isLoading = MutableLiveData(false)
    private val isError = MutableLiveData(false)
    private val trackList = MutableLiveData<MutableList<Track>>()
    private val favoriteList = MutableLiveData<MutableList<Track>>(mutableListOf())



    fun getSearchSongsStream(): Flow<PagingData<Track>> {
        return Pager(PagingConfig(pageSize = CONTENTS_COUNT)) {
            TrackPagingSource(songRepository, "greenday")
        }
            .flow
            .cachedIn(viewModelScope)
    }

    suspend fun searchSong() {
        isLoading.postValue(true) // FIXME
        songRepository.searchSong("greenday", page * DEFAULT_LIMIT)
        isLoading.postValue(false) // FIXME
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
        viewModelScope.launch(Dispatchers.Default) {
            songRepository.updateTrack(track.isFavorite, track.trackId)
        }
    }

    fun getFavoriteList(): LiveData<MutableList<Track>> = favoriteList
    fun getTrackList(): LiveData<MutableList<Track>> = trackList
    fun isLoading(): LiveData<Boolean> = isLoading
    fun isError(): LiveData<Boolean> = isError
}