package com.gabrielcarvalhotp.meumercado.data.remote.repository

import android.app.Application
import com.gabrielcarvalhotp.meumercado.R
import com.gabrielcarvalhotp.meumercado.data.models.LoginModel
import com.gabrielcarvalhotp.meumercado.data.models.users.UserDTO
import com.gabrielcarvalhotp.meumercado.data.models.users.UserModel
import com.gabrielcarvalhotp.meumercado.data.remote.service.UserService
import com.gabrielcarvalhotp.meumercado.domain.repositories.UserRepository
import com.gabrielcarvalhotp.meumercado.listeners.CallbackListener
import java.util.UUID
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    application: Application,
    private val service: UserService,
) : BaseRepository(application), UserRepository {
    override fun createUser(user: UserDTO, listener: CallbackListener<UserModel>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.createUser(user)
        executeCall(call, listener)
    }

    override fun login(login: LoginModel, listener: CallbackListener<UserModel>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.login(login)
        executeCall(call, listener)
    }

    override fun updateUser(user: UserDTO, listener: CallbackListener<Void>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.updateUser(user)
        executeCall(call, listener)
    }

    override fun deleteUser(id: UUID, listener: CallbackListener<Void>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.deleteUser(id)
        executeCall(call, listener)
    }

}