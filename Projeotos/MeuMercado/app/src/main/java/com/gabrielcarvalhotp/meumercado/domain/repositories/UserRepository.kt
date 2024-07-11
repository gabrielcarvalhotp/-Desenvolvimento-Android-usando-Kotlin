package com.gabrielcarvalhotp.meumercado.domain.repositories

import com.gabrielcarvalhotp.meumercado.data.models.LoginModel
import com.gabrielcarvalhotp.meumercado.data.models.users.UserDTO
import com.gabrielcarvalhotp.meumercado.data.models.users.UserModel
import com.gabrielcarvalhotp.meumercado.listeners.CallbackListener
import java.util.UUID


interface UserRepository {
    fun createUser(user: UserDTO, listener: CallbackListener<UserModel>)
    fun login(login: LoginModel, listener: CallbackListener<UserModel>)
    fun updateUser(user: UserDTO, listener: CallbackListener<Void>)
    fun deleteUser(id: UUID, listener: CallbackListener<Void>)

}