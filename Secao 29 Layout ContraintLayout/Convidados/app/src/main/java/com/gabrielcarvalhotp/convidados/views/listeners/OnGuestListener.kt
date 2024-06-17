package com.gabrielcarvalhotp.convidados.views.listeners

import com.gabrielcarvalhotp.convidados.entities.Guest

interface OnGuestListener {
    fun onClick(guest: Guest)
    fun onDelete(guest: Guest)
}