package com.gabrielcarvalhotp.meumercado.data.remote.service

import com.gabrielcarvalhotp.meumercado.data.models.users.LoginDTO
import com.gabrielcarvalhotp.meumercado.data.models.users.UserDTO
import com.gabrielcarvalhotp.meumercado.data.models.users.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID


interface UserService {
    @POST("users")
    fun createUser(@Body user: UserDTO): Call<UserModel>
    @POST("users/login")
    fun login(@Body login: LoginDTO): Call<UserModel>
    @PUT("users")
    fun updateUser(@Body user: UserDTO): Call<Void>
    @DELETE("users/{id}")
    fun deleteUser(@Path("id", encoded = true) id: UUID): Call<Void>
}