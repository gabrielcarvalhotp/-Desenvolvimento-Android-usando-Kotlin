package com.gabrielcarvalhotp.meumercado.data.cache

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.gabrielcarvalhotp.meumercado.MyApplication


class SecurityPreferences(application: Application) {

    private val preferences: SharedPreferences =
        application.getSharedPreferences("meumercadopreferences", Context.MODE_PRIVATE)

    fun store(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun remove(key: String) {
        preferences.edit().remove(key).apply()
    }

    fun get(key: String): String {
        return preferences.getString(key, "") ?: ""
    }

}