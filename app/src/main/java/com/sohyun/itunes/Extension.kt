package com.sohyun.itunes

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.backgroundNotifyObserver() {
    this.postValue(this.value)
}

fun showToastMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}