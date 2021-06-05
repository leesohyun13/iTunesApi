package com.sohyun.itunes.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.databinding.ItemTrackBinding
import com.sohyun.itunes.setImageResOnGlide
import com.sohyun.itunes.setImageUrlOnGlide
import com.sohyun.itunes.ui.base.BaseAdapter
import com.sohyun.itunes.ui.base.BaseViewHolder
import com.sohyun.itunes.ui.base.TrackItemListener

class FavoriteAdapter(
    private val trackItemListener: TrackItemListener
) : BaseAdapter<Track, FavoriteAdapter.ViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder =
        ViewHolder(
            ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    inner class ViewHolder(private val binding: ItemTrackBinding) :
        BaseViewHolder<Track>(binding.root) {
        override fun bind(item: Track) {
            setImageUrlOnGlide(binding.trackImage, item.artworkUrl60)
            setImageResOnGlide(binding.favoriteImage, R.drawable.baseline_star_black_20)
            binding.trackName.text = item.trackName
            binding.artistName.text = item.artistName
            binding.collectionName.text = item.collectionName

            binding.favoriteImage.setOnClickListener {
                trackItemListener.onClickTrackItem(false, item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.trackId == newItem.trackId
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem
    }
}