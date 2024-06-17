package com.devmasterteam.tasks.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devmasterteam.tasks.constants.TaskConstants
import com.devmasterteam.tasks.listeners.CallbackListener
import com.devmasterteam.tasks.models.SecurityPreferences
import com.devmasterteam.tasks.models.entities.PersonModel
import com.devmasterteam.tasks.models.entities.PriorityModel
import com.devmasterteam.tasks.models.repositories.PersonRepository
import com.devmasterteam.tasks.models.repositories.remote.RetrofitClient

class RegisterViewModel(private val application: Application) : AndroidViewModel(application) {
    private val sheredPreferences = SecurityPreferences(application.applicationContext)
    private val repository = PersonRepository(application.applicationContext)
    private var _personModel = MutableLiveData<PersonModel>()
    val personModel: LiveData<PersonModel> = _personModel

    fun create(name: String, email: String, password: String) {
        repository.create(name, email, password, object : CallbackListener<PersonModel> {
            override fun onResponse(obj: PersonModel) {
                sheredPreferences.store(TaskConstants.SHARED.PERSON_KEY, obj.personKey)
                sheredPreferences.store(TaskConstants.SHARED.TOKEN_KEY, obj.token)
                sheredPreferences.store(TaskConstants.SHARED.PERSON_NAME, obj.name)
                RetrofitClient.addRequestHeaders(obj.token, obj.personKey)
                _personModel.value = obj
            }

            override fun onFailure(message: String) {
                Toast.makeText(application.applicationContext, message, Toast.LENGTH_SHORT).show();
            }
        })
    }
}