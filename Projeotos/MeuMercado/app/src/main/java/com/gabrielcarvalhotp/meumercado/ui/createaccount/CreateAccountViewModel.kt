package com.gabrielcarvalhotp.meumercado.ui.createaccount

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabrielcarvalhotp.meumercado.data.models.PostalCodeModel
import com.gabrielcarvalhotp.meumercado.data.models.users.UserDTO
import com.gabrielcarvalhotp.meumercado.data.models.users.UserModel
import com.gabrielcarvalhotp.meumercado.domain.usecases.CreateUserUseCase
import com.gabrielcarvalhotp.meumercado.domain.usecases.GetPostalCodeUseCase
import com.gabrielcarvalhotp.meumercado.domain.usecases.SetUserLoggedData
import com.gabrielcarvalhotp.meumercado.listeners.CallbackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val application: Application,
    private val createUserUseCase: CreateUserUseCase,
    private val setUserLoggedData: SetUserLoggedData,
    private val getPostalCodeUseCase: GetPostalCodeUseCase
) : AndroidViewModel(application) {

    private var _user = MutableLiveData<UserModel>()
    val user: LiveData<UserModel> = _user

    private var _userPostalCode = MutableLiveData<PostalCodeModel>()
    val userPostalCode: LiveData<PostalCodeModel> = _userPostalCode

    private val newUser = UserDTO()

    fun createUser() {
        createUserUseCase(newUser, object: CallbackListener<UserModel> {
            override fun onResponse(obj: UserModel) {
                setUserLoggedData(obj.name, obj.email)
                _user.value = obj
            }

            override fun onFailure(error: String) {
                Toast.makeText(application, error, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun clearNewUserData() {
        newUser.name = ""
        newUser.email = ""
        newUser.password = ""
        newUser.address = ""
        newUser.district = ""
        newUser.city = ""
        newUser.state = ""
        newUser.cep = ""
    }

    fun pushDataNewUser(name: String, email: String, password: String) {
        newUser.name = name
        newUser.email = email
        newUser.password = password
    }

    fun pushDataNewUser(cep: String, address: String, district: String, city: String, state: String) {
        newUser.address = address
        newUser.district = district
        newUser.city = city
        newUser.state = state
        newUser.cep = cep
    }

    fun getPostalCode(cep: String) {
        getPostalCodeUseCase(cep, object: CallbackListener<PostalCodeModel> {
            override fun onResponse(obj: PostalCodeModel) {
                _userPostalCode.value = obj
            }

            override fun onFailure(error: String) {
                Toast.makeText(application, "CEP não encontrado", Toast.LENGTH_LONG).show()
            }
        })
    }
}