package com.sohyun.itunes.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.databinding.FragmentFavoriteBinding
import com.sohyun.itunes.extension.showToastMessage
import com.sohyun.itunes.ui.adapter.TrackItemListener
import com.sohyun.itunes.ui.adapter.TrackPagingAdapter
import com.sohyun.itunes.ui.base.BaseFragment
import com.sohyun.itunes.viewmodel.TrackViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, TrackViewModel>(R.layout.fragment_favorite), TrackItemListener {
    override val viewModel: TrackViewModel by activityViewModels()
    private val trackAdapter = TrackPagingAdapter { track ->  onClickTrackItem(track) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewmodel = viewModel
            favoriteRecyclerview.run {
                adapter = trackAdapter
                addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            }
        }
    }

    override fun onClickTrackItem(track: Track) {
        track.isFavorite = !track.isFavorite
        viewModel.onToggleFavorite(track)
        showToastMessage(requireContext(), getString(R.string.toast_msg_remove_track_item))
    }
}