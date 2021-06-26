package com.sohyun.itunes.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.databinding.FragmentHomeBinding
import com.sohyun.itunes.extension.showToastMessage
import com.sohyun.itunes.ui.adapter.TrackAdapter
import com.sohyun.itunes.ui.base.BaseFragment
import com.sohyun.itunes.ui.adapter.TrackItemListener
import com.sohyun.itunes.viewmodel.TrackViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// FIXME 기존 화면을 유지할 수 있는 방법이 무엇일까?
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
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
                setHasFixedSize(true)
                // FIXME 다른 방법이 뭐가 있을지 고민해보기
                // FIXME 꼭 하단에 닿아야 데이터를 불러와야 하는가?
                // 페이징 라이브러리
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (!recyclerView.canScrollVertically(-1)) {
                            // detect top
                        } else if (!recyclerView.canScrollVertically(1)) {
                            // detect bottom
                            if (trackViewModel.isLoading().value!!) return
                            trackViewModel.increasePage() // FIXME 위치 수정
                            lifecycleScope.launch { trackViewModel.searchSong() }
                        }
                    }
                })
            }
        }
    }

    override fun onClickTrackItem(track: Track) {
        track.isFavorite = !track.isFavorite
        Log.d("TAG", "onClickTrackItem: ${track.isFavorite}")
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

    companion object {
        fun newInstance() = HomeFragment()
    }
}