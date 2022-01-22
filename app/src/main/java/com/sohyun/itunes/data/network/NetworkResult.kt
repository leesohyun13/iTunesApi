package com.sohyun.itunes.data.network

sealed class NetworkResult {
    data class Success<T>(val data: T) : NetworkResult()
    data class Failure(
        val errorBody: String?
    ): NetworkResult()
    data class Loading(val loading: Boolean): NetworkResult()
}