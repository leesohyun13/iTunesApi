package com.sohyun.itunes.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.databinding.FragmentFavoriteBinding
import com.sohyun.itunes.extension.showToastMessage
import com.sohyun.itunes.ui.base.BaseFragment
import com.sohyun.itunes.ui.base.TrackItemListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite), TrackItemListener  {
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewmodel = favoriteViewModel
            lifecycleOwner = this@FavoriteFragment

            favoriteRecyclerview.run {
                favoriteAdapter = FavoriteAdapter(this@FavoriteFragment)
                adapter = favoriteAdapter
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            }
        }

        lifecycleScope.launch { favoriteViewModel.getFavoriteTrackList() }
    }

    override fun onClickTrackItem(isFavorite: Boolean, track: Track) {
        lifecycleScope.launch(Dispatchers.Default) {
            favoriteViewModel.deleteFavoriteTrack(track)
        }
        showToastMessage(requireContext(), getString(R.string.toast_msg_remove_track_item))
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}