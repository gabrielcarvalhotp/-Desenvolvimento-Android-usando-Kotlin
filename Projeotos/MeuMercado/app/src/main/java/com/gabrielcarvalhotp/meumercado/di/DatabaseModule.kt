package com.gabrielcarvalhotp.meumercado.di

import com.gabrielcarvalhotp.meumercado.data.remote.repository.UserRepositoryImpl
import com.gabrielcarvalhotp.meumercado.data.remote.service.UserService
import com.gabrielcarvalhotp.meumercado.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

object DatabaseModule {

}