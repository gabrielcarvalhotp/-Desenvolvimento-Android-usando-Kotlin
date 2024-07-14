package com.gabrielcarvalhotp.meumercado.domain.usecases

import com.gabrielcarvalhotp.meumercado.domain.repositories.UserRepository
import okhttp3.internal.userAgent
import javax.inject.Inject

class GetUserLogged @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Boolean {
        return userRepository.checkUserLogged()
    }
}