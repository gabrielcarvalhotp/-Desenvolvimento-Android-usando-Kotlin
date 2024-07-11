package com.gabrielcarvalhotp.meumercado.domain.usecases

import com.gabrielcarvalhotp.meumercado.data.models.LoginModel
import com.gabrielcarvalhotp.meumercado.data.models.users.UserModel
import com.gabrielcarvalhotp.meumercado.domain.repositories.UserRepository
import com.gabrielcarvalhotp.meumercado.listeners.CallbackListener
import javax.inject.Inject

class GetUserLoginUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(login: LoginModel, listener: CallbackListener<UserModel>) =
        userRepository.login(login, listener)
}