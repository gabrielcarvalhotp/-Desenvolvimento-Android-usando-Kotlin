package com.devmasterteam.tasks.models.repositories

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.listeners.CallbackListener
import com.devmasterteam.tasks.models.entities.PersonModel
import com.devmasterteam.tasks.models.repositories.remote.RetrofitClient
import com.devmasterteam.tasks.models.services.PersonService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class PersonRepository(context: Context): BaseRepository(context) {
    private val service = RetrofitClient.getService(PersonService::class.java)

    fun login(email: String, password: String, listener: CallbackListener<PersonModel>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.login(email, password)
        executeCall(call, listener)
    }

    fun create(name: String, email: String, password: String, listener: CallbackListener<PersonModel>) {
        val call = service.create(name, email, password)
        executeCall(call, listener)
    }
}