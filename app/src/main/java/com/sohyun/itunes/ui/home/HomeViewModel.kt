package com.sohyun.itunes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sohyun.itunes.backgroundNotifyObserver
import com.sohyun.itunes.data.model.SongResponse
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.data.network.NetworkStatus
import com.sohyun.itunes.data.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val songRepository: SongRepository
): ViewModel() {
    private val isLoading = MutableLiveData(false)
    private val songList = MutableLiveData<MutableList<SongResponse.Song>>().apply {
        value = arrayListOf()
    }

    suspend fun searchSong() {
        isLoading.value = true
        val response = songRepository.searchSong("greenday")
        when (response) {
            is NetworkStatus.Success -> {
                songList.value?.addAll(response.data)
                songList.backgroundNotifyObserver()
            }
            is NetworkStatus.Failure -> {} // FIXME
        }
        isLoading.value = false
    }

    suspend fun addFavoriteTrack(track: Track) {
        songRepository.insertItem(track)
    }

    suspend fun deleteFavoriteTrack(track: Track) {
        songRepository.deleteItem(track)
    }

    fun getSongList(): LiveData<MutableList<SongResponse.Song>> = songList
    fun isLoading(): LiveData<Boolean> = isLoading
}