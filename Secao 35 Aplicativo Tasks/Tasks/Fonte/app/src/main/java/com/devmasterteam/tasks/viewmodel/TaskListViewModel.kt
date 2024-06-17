package com.devmasterteam.tasks.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devmasterteam.tasks.constants.TaskConstants
import com.devmasterteam.tasks.listeners.CallbackListener
import com.devmasterteam.tasks.models.entities.TaskModel
import com.devmasterteam.tasks.models.repositories.TaskRepository

class TaskListViewModel(private val application: Application) : AndroidViewModel(application) {

    private val repository = TaskRepository(application.applicationContext)
    private var _taskList = MutableLiveData<List<TaskModel>>()
    val taskList: LiveData<List<TaskModel>> = _taskList
    private var searchFilter = 0
    fun find(searchFilter: Int) {
        this.searchFilter = searchFilter
        val listener = object : CallbackListener<List<TaskModel>> {
            override fun onResponse(obj: List<TaskModel>) {
                _taskList.value = obj
            }

            override fun onFailure(message: String) {}
        }

        when (searchFilter) {
            TaskConstants.FILTER.ALL -> repository.findAll(listener)
            TaskConstants.FILTER.NEXT -> repository.findNext7Days(listener)
            TaskConstants.FILTER.EXPIRED -> repository.findOverdue(listener)
        }
    }

    fun delete(id: Int) {
        repository.delete(id, object : CallbackListener<Boolean> {
            override fun onResponse(obj: Boolean) {
                find(searchFilter)
            }

            override fun onFailure(message: String) {
                Toast.makeText(application.applicationContext, message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun complete(id: Int) {
        repository.complete(id, object : CallbackListener<Boolean> {
            override fun onResponse(obj: Boolean) {
                find(searchFilter)
            }

            override fun onFailure(message: String) {
                Toast.makeText(application.applicationContext, message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun undo(id: Int) {
        repository.undo(id, object : CallbackListener<Boolean> {
            override fun onResponse(obj: Boolean) {
                find(searchFilter)
            }

            override fun onFailure(message: String) {
                Toast.makeText(application.applicationContext, message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}