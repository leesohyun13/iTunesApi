package com.sohyun.itunes.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.databinding.FragmentFavoriteBinding
import com.sohyun.itunes.databinding.ItemTrackBinding
import com.sohyun.itunes.ui.adapter.TrackPagingDiffCallback
import com.sohyun.itunes.ui.adapter.TrackViewHolder
import com.sohyun.itunes.ui.base.BaseFragment
import com.sohyun.itunes.viewmodel.TrackViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, TrackViewModel>(R.layout.fragment_favorite) {
    override val viewModel: TrackViewModel by activityViewModels()
    private val trackAdapter = TrackFavoriteAdapter { viewModel.onToggleFavorite(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            favoriteRecyclerview.run {
                adapter = trackAdapter
                addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.favoriteList.observe(viewLifecycleOwner) {
                it?.let { trackAdapter.submitList(it) }
            }
        }
    }

    private class TrackFavoriteAdapter(
        private val clicked: (Track) -> Track
    ) : ListAdapter<Track, TrackViewHolder>(TrackPagingDiffCallback) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder =
            TrackViewHolder(
                ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                clicked
            )

        override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
            getItem(position)?.let { holder.bind(it) }
        }
    }
}