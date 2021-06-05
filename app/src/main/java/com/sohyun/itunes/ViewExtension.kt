package com.sohyun.itunes

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sohyun.itunes.ui.base.BaseAdapter
import com.sohyun.itunes.ui.base.BaseViewHolder

fun setImageResOnGlide(view: ImageView, resId: Int) {
    Glide.with(view.context)
        .load(resId)
        .into(view)
}

fun setImageUrlOnGlide(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}

@BindingAdapter("bindItems")
fun RecyclerView.bindItems(item: List<Any>?) {
    val adapter = adapter as BaseAdapter<Any, BaseViewHolder<Any>>?
    item?.let { adapter?.addAll(item) }
}