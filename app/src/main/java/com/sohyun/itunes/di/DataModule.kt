package com.sohyun.itunes.di

import android.app.Application
import com.sohyun.itunes.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideSongDb(app: Application) = AppDatabase.invoke(app)

    @Singleton
    @Provides
    fun provideTrackDao(appDatabase: AppDatabase) = appDatabase.trackDao()
}