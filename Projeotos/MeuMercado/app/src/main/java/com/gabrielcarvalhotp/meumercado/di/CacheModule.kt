package com.gabrielcarvalhotp.meumercado.di

import android.app.Application
import com.gabrielcarvalhotp.meumercado.data.cache.SecurityPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
    @Singleton
    @Provides
    fun provideSecurityPreferences(application: Application): SecurityPreferences {
        return SecurityPreferences(application)
    }

}