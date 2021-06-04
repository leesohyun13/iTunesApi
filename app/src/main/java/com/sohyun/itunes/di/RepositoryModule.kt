package com.sohyun.itunes.di

import com.sohyun.itunes.data.repository.SongRepository
import com.sohyun.itunes.data.repository.SongRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindSongRepository(
        songRepositoryImpl: SongRepositoryImpl
    ) : SongRepository
}