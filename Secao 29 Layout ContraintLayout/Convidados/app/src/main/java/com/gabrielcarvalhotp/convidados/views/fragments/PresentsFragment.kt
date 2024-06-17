package com.gabrielcarvalhotp.convidados.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrielcarvalhotp.convidados.databinding.FragmentPresentsBinding
import com.gabrielcarvalhotp.convidados.entities.Guest
import com.gabrielcarvalhotp.convidados.viewmodels.GuestsViewModel
import com.gabrielcarvalhotp.convidados.views.activities.GuestFormActivity
import com.gabrielcarvalhotp.convidados.views.adapters.GuestAdapter
import com.gabrielcarvalhotp.convidados.views.listeners.OnGuestListener

class PresentsFragment : Fragment() {

    private var _binding: FragmentPresentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GuestsViewModel
    private val guestAdapter = GuestAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentPresentsBinding.inflate(inflater, container, false)

        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllGuests.adapter = guestAdapter

        val listener = object : OnGuestListener {
            override fun onClick(guest: Guest) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = bundleOf(Pair("id", guest.guest_id))
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(guest: Guest) {
                viewModel.delete(guest)
                viewModel.findPresents()
            }
        }
        guestAdapter.attachListener(listener)

        viewModel.guestList.observe(viewLifecycleOwner) {
            guestAdapter.updateGuests(it)
        }
        return  binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.findPresents()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}