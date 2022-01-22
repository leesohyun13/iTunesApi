package com.sohyun.itunes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.databinding.ItemTrackBinding

/**
 * iTunes
 * Class: TrackPagingAdapter
 * Created by leesohyun on 2022/01/02.
 *
 * Description:
 */
class TrackPagingAdapter(
    private val clicked: (Track) -> Track
) : PagingDataAdapter<Track, TrackViewHolder>(TrackPagingDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder =
        TrackViewHolder(
            ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clicked
        )

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class TrackViewHolder(
    private val binding: ItemTrackBinding,
    private val clicked: (Track) -> Track
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Track) {
        with(binding) {
            track = item
            favoriteImage.setOnClickListener { track = clicked.invoke(item) }
            executePendingBindings()
        }
    }
}

object TrackPagingDiffCallback : DiffUtil.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem
    }
}
