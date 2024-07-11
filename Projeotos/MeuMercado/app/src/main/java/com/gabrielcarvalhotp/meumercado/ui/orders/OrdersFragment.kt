package com.gabrielcarvalhotp.meumercado.ui.orders

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrielcarvalhotp.meumercado.R
import com.gabrielcarvalhotp.meumercado.databinding.FragmentOrdersBinding
import com.gabrielcarvalhotp.meumercado.ui.ordersdetail.OrdersDetailActivity

class OrdersFragment : Fragment() {

    private val viewModel: OrdersViewModel by viewModels()
    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        binding.rvOrders.adapter = OrdersAdapter(requireContext().applicationContext) { handleOrderItemClick(it) }
        binding.rvOrders.layoutManager = LinearLayoutManager(requireContext().applicationContext)
        return binding.root
    }

    private fun handleOrderItemClick(order: Int) {
        val intent = Intent(requireContext().applicationContext, OrdersDetailActivity::class.java)
        startActivity(intent)
    }
}