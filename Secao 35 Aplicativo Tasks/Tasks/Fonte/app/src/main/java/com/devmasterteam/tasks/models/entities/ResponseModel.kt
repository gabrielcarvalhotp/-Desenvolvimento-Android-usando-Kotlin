package com.devmasterteam.tasks.models.entities

class ResponseModel(message: String = "") {

    private var _status: Boolean = true
    private var _message: String = ""

    init {
        if (message.trim() != "") {
            _status = false
            _message = message
        }
    }

    fun status(): Boolean {
        return _status
    }

    fun message(): String {
        return _message
    }
}