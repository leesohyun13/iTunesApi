package com.sohyun.itunes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sohyun.itunes.data.model.SongResponse
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.data.network.ITunesApi.Companion.DEFAULT_LIMIT
import com.sohyun.itunes.data.network.NetworkStatus
import com.sohyun.itunes.data.repository.SongRepository
import com.sohyun.itunes.extension.backgroundNotifyObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val songRepository: SongRepository
): ViewModel() {
    private var page = 0
    private val isLoading = MutableLiveData(false)
    private val isError = MutableLiveData(false)
    private val songList = MutableLiveData<MutableList<SongResponse.Song>>().apply {
        value = arrayListOf()
    }

    private val favoriteIds = MutableLiveData<MutableList<Int>>().apply {
        value = arrayListOf()
    }

    suspend fun searchSong() {
        isLoading.postValue(true)
        val response = songRepository.searchSong("greenday", page * DEFAULT_LIMIT)
        when (response) {
            is NetworkStatus.Success -> {
                isError.postValue(false)
                response.data?.let {
                    response.data.forEach {
                        if (favoriteIds.value?.contains(it.trackId) == true) {
                            it.isFavorite = true
                        }
                    }
                    songList.value?.addAll(response.data)
                    songList.backgroundNotifyObserver()
                }
            }
            is NetworkStatus.Failure -> isError.postValue(true)
        }
        isLoading.postValue(false)
    }

    fun increasePage() {
        ++page
    }

    suspend fun fetchFavoriteIds() {
        songRepository.getFavoriteId().collect { ids ->
            ids?.let {
                favoriteIds.value?.clear()
                favoriteIds.value?.addAll(it)
            }
        }
    }

    suspend fun addFavoriteTrack(track: Track) {
        songRepository.insertItem(track)
    }

    suspend fun deleteFavoriteTrack(track: Track) {
        songRepository.deleteItem(track)
    }

    fun getSongList(): LiveData<MutableList<SongResponse.Song>> = songList
    fun isLoading(): LiveData<Boolean> = isLoading
    fun isError(): LiveData<Boolean> = isError
}