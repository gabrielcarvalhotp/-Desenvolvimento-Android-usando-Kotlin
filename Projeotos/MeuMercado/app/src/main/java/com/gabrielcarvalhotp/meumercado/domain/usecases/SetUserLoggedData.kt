package com.gabrielcarvalhotp.meumercado.domain.usecases

import com.gabrielcarvalhotp.meumercado.domain.repositories.UserRepository
import javax.inject.Inject

class SetUserLoggedData @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(name: String, email: String) {
        userRepository.saveUserLoggedData(name, email)
    }
}