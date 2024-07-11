package com.devmasterteam.tasks.models.repositories.remote

import com.devmasterteam.tasks.constants.TaskConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    companion object {
        private const val BASE_URL = "http://devmasterteam.com/CursoAndroidAPI/"
        private lateinit var instance: Retrofit
        private var token = ""
        private var personKey = ""

        private fun getInstace(): Retrofit {
            val okHttpClient = OkHttpClient().newBuilder()
            okHttpClient.addInterceptor() { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader(TaskConstants.HEADER.PERSON_KEY, personKey)
                    .addHeader(TaskConstants.HEADER.TOKEN_KEY, token)
                    .build()
                chain.proceed(request)
            }
            if (!Companion::instance.isInitialized) {
                instance = Retrofit.Builder()
                    .client(okHttpClient.build())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance
        }

        fun <T> getService(service: Class<T>): T {
            return getInstace().create(service)
        }

        fun addRequestHeaders(token: String, personKey: String) {
            Companion.token = token
            Companion.personKey = personKey
        }
    }
}