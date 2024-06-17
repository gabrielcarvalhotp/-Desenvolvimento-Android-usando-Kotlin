package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devmasterteam.tasks.constants.TaskConstants
import com.devmasterteam.tasks.listeners.CallbackListener
import com.devmasterteam.tasks.models.entities.PersonModel
import com.devmasterteam.tasks.models.entities.ResponseModel
import com.devmasterteam.tasks.models.repositories.PersonRepository
import com.devmasterteam.tasks.models.SecurityPreferences
import com.devmasterteam.tasks.models.repositories.remote.RetrofitClient

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PersonRepository(application.applicationContext)
    private var _personModel = MutableLiveData<PersonModel>()
    val personModel: LiveData<PersonModel> = _personModel
    private var _responseModel = MutableLiveData<ResponseModel>()
    val responseModel: LiveData<ResponseModel> = _responseModel
    private var _loggedUser = MutableLiveData<Boolean>()
    val loggedUser: LiveData<Boolean> = _loggedUser
    private val sheredPreferences = SecurityPreferences(application.applicationContext)


    fun doLogin(email: String, password: String) {
        repository.login(email, password, object: CallbackListener<PersonModel> {
            override fun onResponse(obj: PersonModel) {
                sheredPreferences.store(TaskConstants.SHARED.PERSON_KEY, obj.personKey)
                sheredPreferences.store(TaskConstants.SHARED.TOKEN_KEY, obj.token)
                sheredPreferences.store(TaskConstants.SHARED.PERSON_NAME, obj.name)
                RetrofitClient.addRequestHeaders(obj.token, obj.personKey)
                _personModel.value = obj
            }

            override fun onFailure(message: String) {
                sheredPreferences.remove("email")
                sheredPreferences.remove("password")
                _responseModel.value = ResponseModel(message)
            }
        })
    }

    fun verifyLoggedUser() {
        val token = sheredPreferences.get(TaskConstants.SHARED.TOKEN_KEY)
        val key = sheredPreferences.get(TaskConstants.SHARED.PERSON_KEY)
        RetrofitClient.addRequestHeaders(token, key)
        _loggedUser.value = key != "" && token != ""
    }

}