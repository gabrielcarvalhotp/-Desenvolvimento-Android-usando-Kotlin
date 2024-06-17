package com.gabrielcarvalhotp.convidados.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabrielcarvalhotp.convidados.entities.Guest
import com.gabrielcarvalhotp.convidados.repositories.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application.applicationContext)

    private val _guestList = MutableLiveData<List<Guest>>()
    val guestList: LiveData<List<Guest>> = _guestList

    private val _guest = MutableLiveData<Guest>()
    val guest: LiveData<Guest> = _guest;

    fun findAll() {
        _guestList.value = repository.findAll()
    }

    fun findById(id: Int) {
        _guest.value = repository.findById(id)
    }

    fun findPresents() {
        _guestList.value = repository.findByPresent(true)
    }

    fun findAbsents() {
        _guestList.value = repository.findByPresent(false)
    }

    fun save(guest: Guest) {
        if (guest.guest_id == 0) {
            repository.insert(guest)
        } else {
            repository.update(guest)
        }
    }

    fun delete(guest: Guest) {
        repository.delete(guest)
    }
}