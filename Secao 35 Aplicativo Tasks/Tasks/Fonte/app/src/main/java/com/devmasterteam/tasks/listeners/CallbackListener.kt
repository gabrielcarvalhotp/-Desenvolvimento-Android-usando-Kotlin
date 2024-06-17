package com.devmasterteam.tasks.listeners

interface CallbackListener<T> {

    fun onResponse(obj: T)
    fun onFailure(message: String)

}