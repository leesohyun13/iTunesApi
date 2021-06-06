package com.sohyun.itunes.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.databinding.FragmentHomeBinding
import com.sohyun.itunes.extension.showToastMessage
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

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (!recyclerView.canScrollVertically(-1)) {
                            // detect top
                        } else if (!recyclerView.canScrollVertically(1)) {
                            // detect bottom
                            if (homeViewModel.isLoading().value!!) return
                            lifecycleScope.launch { homeViewModel.searchSong() }
                            homeViewModel.increasePage()
                        }
                    }
                })
            }
        }

        lifecycleScope.launch(Dispatchers.Default) { homeViewModel.fetchFavoriteIds() }
        lifecycleScope.launch { homeViewModel.searchSong() }
    }

    override fun onClickTrackItem(isFavorite: Boolean, track: Track) {
        when (isFavorite) {
            true -> {
                lifecycleScope.launch(Dispatchers.Default) {
                    homeViewModel.addFavoriteTrack(track)
                }
                showToastMessage(requireContext(), getString(R.string.toast_msg_add_track_item))
            }
            false -> {
                lifecycleScope.launch(Dispatchers.Default) {
                    homeViewModel.deleteFavoriteTrack(track)
                }
                showToastMessage(requireContext(), getString(R.string.toast_msg_remove_track_item))
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}