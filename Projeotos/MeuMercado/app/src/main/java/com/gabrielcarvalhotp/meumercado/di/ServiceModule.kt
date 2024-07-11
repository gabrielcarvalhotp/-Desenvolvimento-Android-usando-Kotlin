package com.gabrielcarvalhotp.meumercado.di

import com.gabrielcarvalhotp.meumercado.data.remote.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideUserService(): UserService {
        return provideService(UserService::class.java)
    }

    private fun <T> provideService(service: Class<T>): T {
        val okHttpClient = OkHttpClient.Builder()
            .build()
            .newBuilder()
            .addInterceptor { chain ->
                val credentials = Credentials.basic("user", "password")
                val request = chain.request()
                request.newBuilder()
                    .addHeader("Authorization", credentials)
                    .build()
                chain.proceed(request)
            }
            .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://127.0.0.1:8080/")
            .build()
            .create(service)
    }
}