package com.sohyun.itunes.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.sohyun.itunes.R
import com.sohyun.itunes.databinding.FragmentFavoriteBinding
import com.sohyun.itunes.ui.adapter.TrackPagingAdapter
import com.sohyun.itunes.ui.base.BaseFragment
import com.sohyun.itunes.viewmodel.TrackViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, TrackViewModel>(R.layout.fragment_favorite) {
    override val viewModel: TrackViewModel by activityViewModels()
    private val trackAdapter = TrackPagingAdapter { track -> viewModel.onToggleFavorite(track) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            favoriteRecyclerview.run {
                adapter = trackAdapter
                addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.trackListFlow.collectLatest { it?.let { trackAdapter.submitData(it) } }
        }
    }
}