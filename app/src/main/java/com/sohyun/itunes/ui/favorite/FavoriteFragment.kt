package com.sohyun.itunes.ui.favorite

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.data.model.ui.TrackUiData
import com.sohyun.itunes.databinding.CellContentBinding
import com.sohyun.itunes.databinding.FragmentFavoriteBinding
import com.sohyun.itunes.ui.base.BaseFragment
import com.sohyun.itunes.ui.base.BaseViewHolder
import com.sohyun.itunes.ui.base.DatBindingAdapter
import com.sohyun.itunes.viewmodel.TrackViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, TrackViewModel>(R.layout.fragment_favorite) {
    override val viewModel: TrackViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            favoriteRecyclerview.run {
                setHasFixedSize(true)
                addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
                adapter = object : DatBindingAdapter<TrackUiData>() {
                    override fun getLayoutResType(position: Int): Int {
                        return getItem(position).layoutRes
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