package com.gabrielcarvalhotp.meumercado.data.remote.repository

import android.app.Application
import com.gabrielcarvalhotp.meumercado.R
import com.gabrielcarvalhotp.meumercado.data.models.PostalCodeModel
import com.gabrielcarvalhotp.meumercado.data.remote.service.PostalCodeService
import com.gabrielcarvalhotp.meumercado.domain.repositories.PostalCodeRepository
import com.gabrielcarvalhotp.meumercado.listeners.CallbackListener
import javax.inject.Inject

class PostalCodeRepositoryImpl @Inject constructor(
    application: Application,
    private val service: PostalCodeService
): BaseRepository(application), PostalCodeRepository {

    override fun findByCep(cep: String, listener: CallbackListener<PostalCodeModel>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.findByCep(cep)
        executeCall(call, listener)
    }

}