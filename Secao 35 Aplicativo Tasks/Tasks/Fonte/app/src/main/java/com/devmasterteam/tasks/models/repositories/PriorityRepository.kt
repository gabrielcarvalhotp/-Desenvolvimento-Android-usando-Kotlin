package com.devmasterteam.tasks.models.repositories

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.listeners.CallbackListener
import com.devmasterteam.tasks.models.entities.PriorityModel
import com.devmasterteam.tasks.models.repositories.local.TaskDatabase
import com.devmasterteam.tasks.models.repositories.remote.RetrofitClient
import com.devmasterteam.tasks.models.services.PersonService
import com.devmasterteam.tasks.models.services.PriorityServices
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriorityRepository(context: Context): BaseRepository(context) {
    private val service = RetrofitClient.getService(PriorityServices::class.java)
    private val database = TaskDatabase.getDatabase(context.applicationContext).priorityDao()

    fun findAll(listener: CallbackListener<List<PriorityModel>>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.findAll()
        executeCall(call, listener)
    }

    fun findAll(): List<PriorityModel> {
        if (!isConnectionAvailable()) {
            return listOf()
        }
        return database.findAll()
    }

    fun save(list: List<PriorityModel>) {
        if (!isConnectionAvailable()) {
            return
        }
        database.clear()
        database.save(list)
    }

}