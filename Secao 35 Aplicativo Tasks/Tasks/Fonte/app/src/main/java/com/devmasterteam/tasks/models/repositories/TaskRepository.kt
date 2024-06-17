package com.devmasterteam.tasks.models.repositories

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.listeners.CallbackListener
import com.devmasterteam.tasks.models.entities.PriorityModel
import com.devmasterteam.tasks.models.entities.TaskModel
import com.devmasterteam.tasks.models.repositories.remote.RetrofitClient
import com.devmasterteam.tasks.models.services.TaskService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PUT

class TaskRepository(context: Context): BaseRepository(context) {
    private val service = RetrofitClient.getService(TaskService::class.java)

    private fun taskList(call: Call<List<TaskModel>>, listener: CallbackListener<List<TaskModel>>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(call, listener)
    }

    fun findAll(listener: CallbackListener<List<TaskModel>>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.findAll()
        taskList(call, listener)
    }

    fun findById(id: Int, listener: CallbackListener<TaskModel>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.findById(id)
        executeCall(call, listener)
    }

    fun findNext7Days(listener: CallbackListener<List<TaskModel>>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.findNext7Days()
        taskList(call, listener)
    }

    fun findOverdue(listener: CallbackListener<List<TaskModel>>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.findOverdue()
        taskList(call, listener)
    }

    fun insert(task: TaskModel, listener: CallbackListener<Boolean>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.insert(task.priorityId, task.description, task.dueDate, task.complete)
        executeCall(call, listener)
    }

    fun update(task: TaskModel, listener: CallbackListener<Boolean>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.update(task.id, task.priorityId, task.description, task.dueDate, task.complete)
        executeCall(call, listener)
    }

    fun delete(id: Int, listener: CallbackListener<Boolean>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.delete(id);
        executeCall(call, listener)
    }


    fun complete(id: Int, listener: CallbackListener<Boolean>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.complete(id);
        executeCall(call, listener)
    }

    fun undo(id: Int, listener: CallbackListener<Boolean>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = service.undo(id);
        executeCall(call, listener)
    }
}