package com.gabrielcarvalhotp.convidados.views.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gabrielcarvalhotp.convidados.R
import com.gabrielcarvalhotp.convidados.databinding.ActivityGuestFormBinding
import com.gabrielcarvalhotp.convidados.entities.Guest
import com.gabrielcarvalhotp.convidados.viewmodels.GuestsViewModel

class GuestFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestsViewModel
    private var guestId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        binding.buttonSave.setOnClickListener { buttonSaveClick() }
        binding.radioButtonPresent.isChecked = true

        viewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            binding.radioButtonPresent.isChecked = it.present
            binding.radioButtonAbsent.isChecked = !it.present
        })
        loadData()
    }

    private fun buttonSaveClick() {
        val name = binding.editName.text.toString()
        val present = binding.radioButtonPresent.isChecked
        val guest = Guest().apply {
            this.guest_id = guestId
            this.name = name
            this.present = present
        }
        viewModel.save(guest)
        Toast.makeText(this, "Atualização feita com sucesso!", Toast.LENGTH_SHORT).show();
        finish()
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt("id", 0)
            viewModel.findById(guestId)
        }
    }
}