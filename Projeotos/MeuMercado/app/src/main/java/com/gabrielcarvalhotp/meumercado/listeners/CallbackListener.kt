package com.gabrielcarvalhotp.meumercado.listeners

import com.gabrielcarvalhotp.meumercado.data.models.StandardException

interface CallbackListener<T> {

    fun onResponse(obj: T)
    fun onFailure(error: String)

}