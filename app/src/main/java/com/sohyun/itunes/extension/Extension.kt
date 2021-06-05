package com.sohyun.itunes.extension

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.backgroundNotifyObserver() {
    this.postValue(this.value)
}