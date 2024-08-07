package com.gabrielcarvalhotp.meumercado.data.models.users

import com.google.gson.annotations.SerializedName

class LoginDTO(email: String, password: String) {

    @SerializedName("email")
    var email: String? = null
    @SerializedName("password")
    var password: String? = null

    init {
        this.email = email
        this.password = password
    }
}