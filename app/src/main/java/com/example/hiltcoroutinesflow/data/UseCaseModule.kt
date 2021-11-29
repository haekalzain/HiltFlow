package com.example.hiltcoroutinesflow.data

import com.example.hiltcoroutinesflow.domain.BiometricUsecase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun provideSecondSampleUseCase(remoteDataSource: RemoteDataSource): BiometricUsecase
}