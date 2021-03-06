package com.sohyun.itunes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.databinding.ItemTrackBinding

class TrackAdapter(
        private val trackItemListener: TrackItemListener
) : ListAdapter<Track, TrackAdapter.ViewHolder>(TrackDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(
                    ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    trackItemListener
            )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
            private val binding: ItemTrackBinding,
            private val trackItemListener: TrackItemListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Track) {
            with(binding) {
                track = item
                favoriteImage.setOnClickListener {
                    trackItemListener.onClickTrackItem(item)
                }
                executePendingBindings()
            }
        }
    }
}

private class TrackDiffCallback : DiffUtil.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.trackId == newItem.trackId
    }

    override fun areContentsTheSame(
            oldItem: Track,
            newItem: Track
    ): Boolean {
        return oldItem == newItem
    }
}
