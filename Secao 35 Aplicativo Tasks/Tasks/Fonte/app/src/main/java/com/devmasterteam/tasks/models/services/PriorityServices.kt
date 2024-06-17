package com.devmasterteam.tasks.models.services

import com.devmasterteam.tasks.models.entities.PriorityModel
import retrofit2.Call
import retrofit2.http.GET

interface PriorityServices {

    @GET("Priority")
    fun findAll(): Call<List<PriorityModel>>
}