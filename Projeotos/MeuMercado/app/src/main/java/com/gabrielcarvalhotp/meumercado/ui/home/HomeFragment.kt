package com.gabrielcarvalhotp.meumercado.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrielcarvalhotp.meumercado.R
import com.gabrielcarvalhotp.meumercado.data.models.EstablishmentsModel
import com.gabrielcarvalhotp.meumercado.databinding.FragmentHomeBinding
import com.gabrielcarvalhotp.meumercado.ui.catalog.CatalogActivity
import com.gabrielcarvalhotp.meumercado.utils.Constants

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        listeners()
        adapter = HomeAdapter { handleEstablishmentItemClick(it) }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(view: View) {
        when (view.id) {
            R.id.item_receive_home -> {
                binding.itemReceiveHome.setTextColor(Color.WHITE)
                binding.itemPickUpStore.setTextColor(R.color.darker_gray)
                binding.select.animate().x(0f).setDuration(300)
            }
            R.id.item_pick_up_store -> {
                binding.itemReceiveHome.setTextColor(R.color.darker_gray)
                binding.itemPickUpStore.setTextColor(Color.WHITE)
                binding.select.animate().x(binding.itemPickUpStore.width.toFloat()).setDuration(300)
            }
        }
    }

    private fun listeners() {
        binding.itemReceiveHome.setOnClickListener(this)
        binding.itemPickUpStore.setOnClickListener(this)
    }

    private fun handleEstablishmentItemClick(establishment: EstablishmentsModel) {
        val intent = Intent(context, CatalogActivity::class.java)
        intent.putExtra(Constants.EXTRAS.ESTABLISHMENT, establishment)
        startActivity(intent)
    }
}