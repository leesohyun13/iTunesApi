package com.sohyun.itunes.extension

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sohyun.itunes.R
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.ui.adapter.TrackAdapter

@BindingAdapter("bindGlideImage")
fun ImageView.bindGlideImage(url: String) {
    Glide.with(context)
            .load(url)
            .error(R.color.black)
            .into(this)
}

@BindingAdapter("bindResOnGlide")
fun ImageView.bindResOnGlide(resId: Int) {
    Glide.with(context)
            .load(resId)
            .into(this)
}

@BindingAdapter("bindItems")
fun RecyclerView.bindItems(item: MutableList<Track>?) {
    val adapter = adapter as TrackAdapter
    item?.let { adapter.submitList(item.toMutableList()) }
}

fun showToastMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}