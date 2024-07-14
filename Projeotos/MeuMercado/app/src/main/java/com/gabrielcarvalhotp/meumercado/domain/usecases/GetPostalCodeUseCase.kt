package com.gabrielcarvalhotp.meumercado.domain.usecases

import com.gabrielcarvalhotp.meumercado.data.models.PostalCodeModel
import com.gabrielcarvalhotp.meumercado.domain.repositories.PostalCodeRepository
import com.gabrielcarvalhotp.meumercado.listeners.CallbackListener
import javax.inject.Inject

class GetPostalCodeUseCase @Inject constructor(
    private val postalCodeRepository: PostalCodeRepository
) {
    operator fun invoke(cep: String, listener: CallbackListener<PostalCodeModel>) = postalCodeRepository.findByCep(cep, listener)
}