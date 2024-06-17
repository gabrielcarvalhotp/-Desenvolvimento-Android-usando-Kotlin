package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.devmasterteam.tasks.constants.TaskConstants
import com.devmasterteam.tasks.models.SecurityPreferences
import java.security.SecurityPermission

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)

    fun logout() {
        securityPreferences.remove(TaskConstants.SHARED.PERSON_KEY)
        securityPreferences.remove(TaskConstants.SHARED.TOKEN_KEY)
        securityPreferences.remove(TaskConstants.SHARED.PERSON_NAME)    
    }

    fun getUserName(): String {
        return securityPreferences.get(TaskConstants.SHARED.PERSON_NAME)
    }
}