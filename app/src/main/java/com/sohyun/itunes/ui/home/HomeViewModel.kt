package com.sohyun.itunes.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.data.network.NetworkStatus
import com.sohyun.itunes.data.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val songRepository: SongRepository
): ViewModel() {
    private val isLoading = MutableLiveData(false)
    private val trackList = MutableLiveData<MutableList<Track>>() // FIXME 페이징 처리를 하려고 MutableList 사용

    private suspend fun searchSong() {
        isLoading.value = true
        val response = songRepository.searchSong("greenday")
        when (response) {
            is NetworkStatus.Success -> {
                Log.d("TAG", "searchSong: ${response.data}")
            }
            is NetworkStatus.Failure -> {} // FIXME
        }
        isLoading.value = false
    }

    init {
        viewModelScope.launch { searchSong() }
    }
}