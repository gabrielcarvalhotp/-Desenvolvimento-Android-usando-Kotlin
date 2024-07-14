package com.gabrielcarvalhotp.meumercado.data.models

import com.google.gson.annotations.SerializedName

class PostalCodeModel(
    @SerializedName("bairro")
    val district: String,
    @SerializedName("cep")
    val postalCode: String,
    @SerializedName("complemento")
    val complement: String,
    @SerializedName("ddd")
    val areaCode: String,
    @SerializedName("gia")
    val gia: String,
    @SerializedName("ibge")
    val ibge: String,
    @SerializedName("localidade")
    val city: String,
    @SerializedName("logradouro")
    val street: String,
    @SerializedName("siafi")
    val siafi: String,
    @SerializedName("uf")
    val state: String,
    @SerializedName("unidade")
    val unit: String,
)