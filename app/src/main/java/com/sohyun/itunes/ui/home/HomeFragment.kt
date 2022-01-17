package com.sohyun.itunes.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.databinding.FragmentHomeBinding
import com.sohyun.itunes.extension.showToastMessage
import com.sohyun.itunes.ui.adapter.TrackPagingAdapter
import com.sohyun.itunes.ui.base.BaseFragment
import com.sohyun.itunes.viewmodel.TrackViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

// 기존 화면을 유지할 수 있는 방법이 무엇일까?
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, TrackViewModel>(R.layout.fragment_home) {
    override val viewModel: TrackViewModel by activityViewModels()
    private val trackAdapter = TrackPagingAdapter { track ->  onClickTrackItem(track) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewmodel = viewModel
            homeRecyclerView.run {
                adapter = trackAdapter
                addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.trackListFlow.collectLatest { it?.let { trackAdapter.submitData(it) } }
        }
    }

    private fun onClickTrackItem(track: Track) {
        track.isFavorite = !track.isFavorite
        viewModel.onToggleFavorite(track)
        when (track.isFavorite) {
            true -> showToastMessage(requireContext(), getString(R.string.toast_msg_add_track_item))
            false -> showToastMessage(requireContext(), getString(R.string.toast_msg_remove_track_item))
        }
    }
}