package com.sohyun.itunes.di

import com.sohyun.itunes.data.network.ITunesApi
import com.sohyun.itunes.data.network.ITunesApi.Companion.ITUNES_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideKakaoApi(): ITunesApi {
        return Retrofit.Builder()
            .baseUrl(ITUNES_BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ITunesApi::class.java)
    }
}