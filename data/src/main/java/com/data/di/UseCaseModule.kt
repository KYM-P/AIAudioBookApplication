package com.data.di

import com.domain.repository.UploadImageRepository
import com.domain.usecase.UploadImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUploadImageUseCase(
        uploadImageRepository: UploadImageRepository
    ): UploadImageUseCase = UploadImageUseCase(uploadImageRepository)

}