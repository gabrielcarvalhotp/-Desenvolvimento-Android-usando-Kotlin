package com.devmasterteam.tasks.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devmasterteam.tasks.listeners.CallbackListener
import com.devmasterteam.tasks.models.entities.ResponseModel
import com.devmasterteam.tasks.models.entities.TaskModel
import com.devmasterteam.tasks.models.repositories.TaskRepository

class TaskFormViewModel(private val application: Application) : AndroidViewModel(application) {

    private val repository = TaskRepository(application.applicationContext)
    private var _responseModel = MutableLiveData<ResponseModel>()
    private var _task = MutableLiveData<TaskModel>()
    val task: LiveData<TaskModel> = _task
    val responseModel: LiveData<ResponseModel> = _responseModel

    fun findById(id: Int) {
        repository.findById(id, object : CallbackListener<TaskModel> {
            override fun onResponse(obj: TaskModel) {
                _task.value = obj
            }

            override fun onFailure(message: String) {
                Toast.makeText(application.applicationContext, message, Toast.LENGTH_SHORT).show();
            }
        })
    }


    fun save(task: TaskModel) {
        val listener = object : CallbackListener<Boolean> {
            override fun onResponse(obj: Boolean) {
                _responseModel.value = ResponseModel()
            }

            override fun onFailure(message: String) {
                _responseModel.value = ResponseModel(message)
            }
        }
        if (task.id <= 0) {
            repository.insert(task, listener)
        } else {
            repository.update(task, listener)
        }

    }
}