package com.gabrielcarvalhotp.meumercado.domain.usecases

import com.gabrielcarvalhotp.meumercado.data.models.users.UserDTO
import com.gabrielcarvalhotp.meumercado.data.models.users.UserModel
import com.gabrielcarvalhotp.meumercado.domain.repositories.UserRepository
import com.gabrielcarvalhotp.meumercado.listeners.CallbackListener
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(user: UserDTO, listener: CallbackListener<UserModel>) {
        userRepository.createUser(user, listener)
    }
}