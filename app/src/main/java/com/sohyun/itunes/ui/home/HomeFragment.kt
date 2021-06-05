package com.sohyun.itunes.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.databinding.FragmentHomeBinding
import com.sohyun.itunes.showToastMessage
import com.sohyun.itunes.ui.base.BaseFragment
import com.sohyun.itunes.ui.base.TrackItemListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), TrackItemListener {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var trackAdapter: TrackAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewmodel = homeViewModel
            lifecycleOwner = this@HomeFragment

            homeRecyclerView.run {
                trackAdapter = TrackAdapter(this@HomeFragment)
                adapter = trackAdapter
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            }
        }

        lifecycleScope.launch { homeViewModel.searchSong() }
    }

    override fun onClickTrackItem(isFavorite: Boolean, track: Track) {
        when (isFavorite) {
            true -> {
                lifecycleScope.launch(Dispatchers.Default) {
                    homeViewModel.addFavoriteTrack(track)
                }
                showToastMessage(requireContext(), "좋아요 목록에 추가되었습니다.")
            }
            false -> {
                lifecycleScope.launch(Dispatchers.Default) {
                    homeViewModel.deleteFavoriteTrack(track)
                }
                showToastMessage(requireContext(), "좋아요 목록에 삭제되었습니다.")
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}