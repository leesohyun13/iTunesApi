package com.sohyun.itunes

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.backgroundNotifyObserver() {
    this.postValue(this.value)
}