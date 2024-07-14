package com.gabrielcarvalhotp.meumercado.domain.repositories

import com.gabrielcarvalhotp.meumercado.data.models.users.LoginDTO
import com.gabrielcarvalhotp.meumercado.data.models.users.UserDTO
import com.gabrielcarvalhotp.meumercado.data.models.users.UserModel
import com.gabrielcarvalhotp.meumercado.listeners.CallbackListener
import java.util.UUID


interface UserRepository {
    fun createUser(user: UserDTO, listener: CallbackListener<UserModel>)
    fun login(login: LoginDTO, listener: CallbackListener<UserModel>)
    fun updateUser(user: UserDTO, listener: CallbackListener<Void>)
    fun deleteUser(id: UUID, listener: CallbackListener<Void>)
    fun checkUserLogged(): Boolean
    fun saveUserLoggedData(name: String, email: String)
}