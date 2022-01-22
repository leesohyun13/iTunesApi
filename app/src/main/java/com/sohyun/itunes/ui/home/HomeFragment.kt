package com.sohyun.itunes.ui.home

import android.os.Bundle
import android.util.Log
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

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, TrackViewModel>(R.layout.fragment_home) {
    private val TAG = HomeFragment::class.java.simpleName
    override val viewModel: TrackViewModel by activityViewModels()
    private val trackAdapter = TrackPagingAdapter { track -> viewModel.onToggleFavorite(track) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            homeRecyclerView.run {
                adapter = trackAdapter
                setHasFixedSize(true)
                addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.trackListFlow.collectLatest { it?.let {
                Log.d(TAG, "track list response $it")
                trackAdapter.submitData(it)
            } }
        }
    }
}