package com.gabrielcarvalhotp.meumercado.ui.login

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabrielcarvalhotp.meumercado.data.models.users.LoginDTO
import com.gabrielcarvalhotp.meumercado.data.models.users.UserModel
import com.gabrielcarvalhotp.meumercado.domain.usecases.GetUserLogged
import com.gabrielcarvalhotp.meumercado.domain.usecases.GetUserLoginUseCase
import com.gabrielcarvalhotp.meumercado.domain.usecases.SetUserLoggedData
import com.gabrielcarvalhotp.meumercado.listeners.CallbackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val application: Application,
    private val getUserLoginUseCase: GetUserLoginUseCase,
    private val getUserLogged: GetUserLogged,
    private val setUserLoggedData: SetUserLoggedData
): AndroidViewModel(application) {

    private var _user = MutableLiveData<UserModel>()
    val user: LiveData<UserModel> = _user

    private var _userLogged = MutableLiveData<Boolean>()
    val userLogged: LiveData<Boolean> = _userLogged

    fun login(email: String, password: String) {
        val login = LoginDTO(email, password)
        getUserLoginUseCase(login, object: CallbackListener<UserModel> {
            override fun onResponse(obj: UserModel) {
                setUserLoggedData(obj.name, obj.email)
                _user.value = obj
            }

            override fun onFailure(error: String) {
                Toast.makeText(application, error, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun checkUserLogged() {
        _userLogged.value = getUserLogged()
    }
}