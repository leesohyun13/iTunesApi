package com.sohyun.itunes.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.SongResponse
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.databinding.ItemTrackBinding
import com.sohyun.itunes.setImageResOnGlide
import com.sohyun.itunes.setImageUrlOnGlide
import com.sohyun.itunes.ui.base.BaseAdapter
import com.sohyun.itunes.ui.base.BaseViewHolder
import com.sohyun.itunes.ui.base.TrackItemListener

class TrackAdapter(
    val trackItemListener: TrackItemListener
) : BaseAdapter<SongResponse.Song, TrackAdapter.ViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackAdapter.ViewHolder =
        ViewHolder(
            ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    inner class ViewHolder(private val binding: ItemTrackBinding) :
        BaseViewHolder<SongResponse.Song>(binding.root) {

        override fun bind(item: SongResponse.Song) {
            setImageUrlOnGlide(binding.trackImage, item.artworkUrl60)
            setFavoriteStateImage(item.isFavorite, binding.favoriteImage)
            binding.trackName.text = item.trackName
            binding.artistName.text = item.artistName
            binding.collectionName.text = item.collectionName

            binding.favoriteImage.setOnClickListener {
                val isFavorite = !item.isFavorite
                item.isFavorite = isFavorite
                updateItem(item)
                trackItemListener.onClickTrackItem(
                    isFavorite,
                    Track(
                        item.trackId,
                        item.trackName,
                        item.collectionName,
                        item.artworkUrl60,
                        item.artistName,
                        item.artistName,
                        isFavorite
                    )
                )
            }
        }
    }

    fun setFavoriteStateImage(isFavorite: Boolean, imageView: ImageView) {
        if (isFavorite) setImageResOnGlide(imageView, R.drawable.baseline_star_black_20)
        else setImageResOnGlide(imageView, R.drawable.baseline_star_border_black_20)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

private class DiffCallback : DiffUtil.ItemCallback<SongResponse.Song>() {
    override fun areItemsTheSame(oldItem: SongResponse.Song, newItem: SongResponse.Song): Boolean {
        return oldItem.trackId == newItem.trackId
    }

    override fun areContentsTheSame(
        oldItem: SongResponse.Song,
        newItem: SongResponse.Song
    ): Boolean {
        return oldItem == newItem
    }
}