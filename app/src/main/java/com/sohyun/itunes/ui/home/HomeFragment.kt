package com.sohyun.itunes.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.data.model.ui.TrackUiData
import com.sohyun.itunes.databinding.CellContentBinding
import com.sohyun.itunes.databinding.FragmentHomeBinding
import com.sohyun.itunes.ui.base.*
import com.sohyun.itunes.viewmodel.TrackViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, TrackViewModel>(R.layout.fragment_home) {
    private val TAG = HomeFragment::class.java.simpleName
    override val viewModel: TrackViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            homeRecyclerView.run {
                setHasFixedSize(true)
                addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
                adapter = object : BasePagingAdapter<TrackUiData>(BaseDiffCallback()) {
                    override fun getLayoutResType(position: Int): Int {
                        return getItem(position)?.layoutRes ?: 0
                    }

                    override fun onCreateViewHolder(
                        parent: ViewGroup,
                        viewType: Int
                    ): BaseViewHolder<TrackUiData> {
                        return super.onCreateViewHolder(parent, viewType).apply {
                            when (this.dataBinding) {
                                is CellContentBinding -> initContent(this.dataBinding as CellContentBinding)
                            }
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.trackListFlow.collectLatest { it?.let {
                Log.d(TAG, "track list response $it")
                (binding.homeRecyclerView.adapter as PagingDataAdapter<TrackUiData, *>).submitData(it)
            }}
        }
    }

    private fun initContent(databinding: CellContentBinding) {
        databinding.trackRecyclerView.run {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            adapter = object : DatBindingAdapter<Track>() {
                override fun getLayoutResType(position: Int): Int {
                    return R.layout.item_track
                }
            }
        }
    }
}