package com.sohyun.itunes.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.data.page.TrackPagingSource
import com.sohyun.itunes.data.page.TrackPagingSource.Companion.CONTENTS_COUNT
import com.sohyun.itunes.data.repository.SongRepository
import com.sohyun.itunes.extension.showToastMessage
import com.sohyun.itunes.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(
    @ActivityContext private val context: Context,
    private val songRepository: SongRepository
): BaseViewModel() {
    private val isLoading = MutableLiveData(false)
    private val isError = MutableLiveData(false)

    private val _favoriteList = MutableLiveData<MutableList<Track>>(mutableListOf())
    val favoriteList: LiveData<MutableList<Track>> = _favoriteList

    private val _trackListFlow: MutableStateFlow<PagingData<Track>?> = MutableStateFlow(null)
    val trackListFlow: StateFlow<PagingData<Track>?> = _trackListFlow.asStateFlow()

    init {
        viewModelScope.launch {
            getSearchSongsStream().collectLatest { _trackListFlow.value = it }
            fetchFavoriteTrackList()
        }
    }

    private fun getSearchSongsStream(): Flow<PagingData<Track>> {
        return Pager(PagingConfig(pageSize = CONTENTS_COUNT)) {
            TrackPagingSource(songRepository, "greenday")
        }
            .flow
            .cachedIn(viewModelScope)
    }

    private suspend fun fetchFavoriteTrackList() {
        songRepository.getFavoriteList().collectLatest { tracks ->
            _favoriteList.postValue(tracks)
        }
    }

    fun onToggleFavorite(track: Track) {
        track.isFavorite = !track.isFavorite
        viewModelScope.launch(Dispatchers.Default) {
            songRepository.updateTrack(track.isFavorite, track.trackId)
        }
        when (track.isFavorite) {
            true -> showToastMessage(context, context.resources.getString(R.string.toast_msg_add_track_item))
            else -> showToastMessage(context, context.resources.getString(R.string.toast_msg_remove_track_item))
        }
    }

    fun isLoading(): LiveData<Boolean> = isLoading
    fun isError(): LiveData<Boolean> = isError
}