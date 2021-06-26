package com.sohyun.itunes.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.databinding.FragmentHomeBinding
import com.sohyun.itunes.extension.showToastMessage
import com.sohyun.itunes.ui.adapter.TrackAdapter
import com.sohyun.itunes.ui.adapter.TrackItemListener
import com.sohyun.itunes.ui.base.BaseFragment
import com.sohyun.itunes.viewmodel.TrackViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// 기존 화면을 유지할 수 있는 방법이 무엇일까?
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), TrackItemListener {
    private val trackViewModel: TrackViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewmodel = trackViewModel
            lifecycleOwner = this@HomeFragment

            homeRecyclerView.run {
                val trackAdapter = TrackAdapter(this@HomeFragment)
                adapter = trackAdapter
                addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (!recyclerView.canScrollVertically(1)) {
                            // detect bottom
                            if (trackViewModel.isLoading().value!!) return
                            trackViewModel.increasePage()
                            lifecycleScope.launch { trackViewModel.searchSong() }
                        }
                    }
                })
            }
        }
    }

    override fun onClickTrackItem(track: Track) {
        track.isFavorite = !track.isFavorite
        trackViewModel.onToggleFavorite(track)
        when (track.isFavorite) {
            true -> showToastMessage(requireContext(), getString(R.string.toast_msg_add_track_item))
            false -> showToastMessage(requireContext(), getString(R.string.toast_msg_remove_track_item))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}