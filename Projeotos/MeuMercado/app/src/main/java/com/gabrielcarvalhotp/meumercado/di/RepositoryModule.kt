package com.gabrielcarvalhotp.meumercado.di

import android.app.Application
import com.gabrielcarvalhotp.meumercado.data.remote.repository.UserRepositoryImpl
import com.gabrielcarvalhotp.meumercado.data.remote.service.UserService
import com.gabrielcarvalhotp.meumercado.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideUserRepository(userRepositoryImpl :UserRepositoryImpl): UserRepository
}