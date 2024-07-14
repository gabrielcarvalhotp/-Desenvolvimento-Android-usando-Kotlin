package com.gabrielcarvalhotp.meumercado.domain.repositories

import com.gabrielcarvalhotp.meumercado.data.models.PostalCodeModel
import com.gabrielcarvalhotp.meumercado.listeners.CallbackListener

interface PostalCodeRepository {

    fun findByCep(cep: String, listener: CallbackListener<PostalCodeModel>)
}