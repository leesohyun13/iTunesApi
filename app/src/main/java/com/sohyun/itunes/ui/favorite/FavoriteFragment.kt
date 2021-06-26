package com.sohyun.itunes.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.databinding.FragmentFavoriteBinding
import com.sohyun.itunes.extension.showToastMessage
import com.sohyun.itunes.ui.base.BaseFragment
import com.sohyun.itunes.ui.adapter.TrackItemListener
import com.sohyun.itunes.ui.adapter.TrackAdapter
import com.sohyun.itunes.viewmodel.TrackViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite), TrackItemListener {
    private val trackViewModel: TrackViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewmodel = trackViewModel
            lifecycleOwner = this@FavoriteFragment

            favoriteRecyclerview.run {
                val favoriteAdapter = TrackAdapter(this@FavoriteFragment)
                adapter = favoriteAdapter
                addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            }
        }
    }

    override fun onClickTrackItem(track: Track) {
        track.isFavorite = !track.isFavorite
        trackViewModel.onToggleFavorite(track)
        showToastMessage(requireContext(), getString(R.string.toast_msg_remove_track_item))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}