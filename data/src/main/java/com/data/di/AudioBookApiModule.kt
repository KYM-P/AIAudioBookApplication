package com.data.di

import com.data.api.AudioBookApiService
import com.data.datasource.remote.AudioBookApiDataSource
import com.data.repositoryImpl.UploadImageRepositoryImpl
import com.domain.repository.UploadImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AudioBookApiModule {

    @Provides
    @Singleton
    fun provideUploadImageRepository(
        audioBookApiDataSource: AudioBookApiDataSource
    ): UploadImageRepository = UploadImageRepositoryImpl(audioBookApiDataSource)

    @Provides
    @Singleton
    fun provideAudioBookApiDataSource(
        audioBookApiService: AudioBookApiService
    ): AudioBookApiDataSource = AudioBookApiDataSource(audioBookApiService)

}