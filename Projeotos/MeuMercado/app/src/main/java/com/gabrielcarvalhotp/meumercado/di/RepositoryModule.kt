package com.gabrielcarvalhotp.meumercado.di

import com.gabrielcarvalhotp.meumercado.data.remote.repository.PostalCodeRepositoryImpl
import com.gabrielcarvalhotp.meumercado.data.remote.repository.UserRepositoryImpl
import com.gabrielcarvalhotp.meumercado.domain.repositories.PostalCodeRepository
import com.gabrielcarvalhotp.meumercado.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun providePostalCodeRepository(postalCodeRepositoryImpl: PostalCodeRepositoryImpl): PostalCodeRepository
}