package com.gabrielcarvalhotp.meumercado.ui.login

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabrielcarvalhotp.meumercado.R
import com.gabrielcarvalhotp.meumercado.data.models.LoginModel
import com.gabrielcarvalhotp.meumercado.data.models.users.UserModel
import com.gabrielcarvalhotp.meumercado.domain.repositories.UserRepository
import com.gabrielcarvalhotp.meumercado.domain.usecases.GetUserLoginUseCase
import com.gabrielcarvalhotp.meumercado.listeners.CallbackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val application: Application,
    private val getUserLoginUseCase: GetUserLoginUseCase
): AndroidViewModel(application) {

    private var _user = MutableLiveData<UserModel>()
    val user: LiveData<UserModel> = _user

    fun login(email: String, password: String) {
        val login = LoginModel(email, password)
        getUserLoginUseCase(login, object: CallbackListener<UserModel> {
            override fun onResponse(obj: UserModel) {
                _user.value = obj
            }

            override fun onFailure(error: String) {
                Toast.makeText(application, error, Toast.LENGTH_LONG).show()
            }
        })
    }
}