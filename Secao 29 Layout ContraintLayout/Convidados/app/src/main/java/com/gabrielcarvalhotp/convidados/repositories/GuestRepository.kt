package com.gabrielcarvalhotp.convidados.repositories

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.contentValuesOf
import com.gabrielcarvalhotp.convidados.connections.GuestDatabase
import com.gabrielcarvalhotp.convidados.dao.GuestDAO
import com.gabrielcarvalhotp.convidados.entities.Guest

class GuestRepository(context: Context) {

    private val dao = GuestDatabase.getInstance(context).guestDAO()

    fun insert(guest: Guest) {
        dao.insert(guest)
    }

    fun update(guest: Guest) {
        dao.update(guest)
    }

    fun delete(guest: Guest) {
        dao.delete(guest)
    }

    fun findAll(): List<Guest> {
        return dao.findAll()
    }

    fun findById(id: Int): Guest? {
        return dao.findById(id)
    }

    fun findByPresent(present: Boolean): List<Guest> {
        return dao.findByPresent(present)
    }
}