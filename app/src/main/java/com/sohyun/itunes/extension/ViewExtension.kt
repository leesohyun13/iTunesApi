package com.sohyun.itunes.extension

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sohyun.itunes.R

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

fun showToastMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}