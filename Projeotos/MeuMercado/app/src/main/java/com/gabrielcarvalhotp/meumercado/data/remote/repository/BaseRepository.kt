package com.gabrielcarvalhotp.meumercado.data.remote.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.gabrielcarvalhotp.meumercado.R
import com.gabrielcarvalhotp.meumercado.data.models.StandardException
import com.gabrielcarvalhotp.meumercado.listeners.CallbackListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository(val context: Context) {
    private fun failureResponse(error: StandardException): String {
        return Gson().fromJson(error.message, String::class.java)
    }

    fun <T> executeCall(call: Call<T>, listener: CallbackListener<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() == 200) {
                    response.body()!!.let { listener.onResponse(it) }
                } else {
                    val message = failureResponse(response.body()!! as StandardException)
                    listener.onFailure(message)
                }
            }

            override fun onFailure(p0: Call<T>, p1: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    fun isConnectionAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        val connected = when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            else -> false
        }
        return connected
    }
}