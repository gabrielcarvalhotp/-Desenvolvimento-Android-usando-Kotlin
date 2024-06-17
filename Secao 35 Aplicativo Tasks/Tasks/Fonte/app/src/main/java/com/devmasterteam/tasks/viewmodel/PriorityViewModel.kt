package com.devmasterteam.tasks.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devmasterteam.tasks.listeners.CallbackListener
import com.devmasterteam.tasks.models.entities.PriorityModel
import com.devmasterteam.tasks.models.repositories.PriorityRepository
import com.devmasterteam.tasks.models.repositories.local.TaskDatabase

class PriorityViewModel(application: Application) : AndroidViewModel(application) {
    private var _priorityList = MutableLiveData<List<PriorityModel>>()
    val priorityList: LiveData<List<PriorityModel>> = _priorityList
    private val repository = PriorityRepository(application.applicationContext)

    companion object {
        private val priority = mutableListOf<PriorityModel>()
        fun findById(id: Int): PriorityModel? {
            return priority.find { priorityModel -> priorityModel.id == id }
        }

        fun addPriority(priorityModel: PriorityModel) {
            priority.add(priorityModel)
        }
    }


    fun saveAll() {
        repository.findAll(object : CallbackListener<List<PriorityModel>> {
            override fun onResponse(obj: List<PriorityModel>) {
                obj.forEach { addPriority(it) }
                repository.save(obj)
            }

            override fun onFailure(message: String) {

            }
        })
    }

    fun findAll() {
        _priorityList.value = priority
    }

    fun loadPriorities() {
        repository.findAll().forEach { addPriority(it) }
    }
}