package com.gabrielcarvalhotp.convidados.views.viewholders

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcarvalhotp.convidados.databinding.RowGuestBinding
import com.gabrielcarvalhotp.convidados.entities.Guest
import com.gabrielcarvalhotp.convidados.views.listeners.OnGuestListener

class GuestViewHolder(private val binding: RowGuestBinding, private val listener: OnGuestListener) : RecyclerView.ViewHolder(binding.root) {

    fun bind(guest: Guest) {
        binding.textName.text = guest.name

        binding.textName.setOnClickListener {
            listener.onClick(guest)
        }

        binding.textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado!")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("SIM") {x, y ->
                    listener.onDelete(guest)
                }
                .setNegativeButton("NÃO", null)
                .create()
                .show()
            true
        }
    }
}