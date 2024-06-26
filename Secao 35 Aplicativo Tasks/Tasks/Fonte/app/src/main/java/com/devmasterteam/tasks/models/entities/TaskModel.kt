package com.devmasterteam.tasks.models.entities

import com.google.gson.annotations.SerializedName

class TaskModel {
    @SerializedName("Id")
    var id: Int = 0
    @SerializedName("PriorityId")
    var priorityId: Int = 0
    @SerializedName("Description")
    var description: String = ""
    @SerializedName("DueDate")
    var dueDate: String = ""
    @SerializedName("Complete")
    var complete: Boolean = false
}