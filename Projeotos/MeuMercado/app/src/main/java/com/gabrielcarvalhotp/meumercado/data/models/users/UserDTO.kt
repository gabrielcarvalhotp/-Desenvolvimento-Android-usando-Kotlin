package com.gabrielcarvalhotp.meumercado.data.models.users

import com.google.gson.annotations.SerializedName

class UserDTO {
    @SerializedName("name")
    var name: String = ""
    @SerializedName("email")
    var email: String = ""
    @SerializedName("password")
    var password: String = ""
    @SerializedName("address")
    var address: String = ""
    @SerializedName("district")
    var district: String = ""
    @SerializedName("city")
    var city: String = ""
    @SerializedName("state")
    var state: String = ""
    @SerializedName("cep")
    var cep: String = ""
}