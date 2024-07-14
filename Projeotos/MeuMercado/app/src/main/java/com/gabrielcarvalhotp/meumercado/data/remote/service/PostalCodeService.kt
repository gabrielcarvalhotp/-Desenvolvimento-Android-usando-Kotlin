package com.gabrielcarvalhotp.meumercado.data.remote.service

import com.gabrielcarvalhotp.meumercado.data.models.PostalCodeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostalCodeService {
    @GET("{cep}/json")
    fun findByCep(@Path("cep") postalCode: String): Call<PostalCodeModel>

}