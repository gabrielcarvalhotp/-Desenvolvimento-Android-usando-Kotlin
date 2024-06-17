package com.gabrielcarvalhotp.convidados.views.adapters

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcarvalhotp.convidados.databinding.RowGuestBinding
import com.gabrielcarvalhotp.convidados.entities.Guest
import com.gabrielcarvalhotp.convidados.views.listeners.OnGuestListener
import com.gabrielcarvalhotp.convidados.views.viewholders.GuestViewHolder

class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>() {
    private var guestsList = listOf<Guest>()
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(item, listener)
    }

    override fun getItemCount(): Int {
        return guestsList.count()
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guestsList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateGuests(guestsList: List<Guest>) {
        this.guestsList = guestsList
        notifyDataSetChanged()
    }

    fun attachListener(listener: OnGuestListener) {
        this.listener = listener
    }

}