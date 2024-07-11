package com.gabrielcarvalhotp.meumercado.ui.orders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcarvalhotp.meumercado.databinding.OrderItemBinding

class OrdersAdapter(
    context: Context,
    private val onClick: (Int) -> Unit
): RecyclerView.Adapter<OrdersAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: OrderItemBinding, val onClick: (Int) -> Unit): RecyclerView.ViewHolder(binding.root) {
        private var currentOrder: Int? = null
        init {
            binding.root.setOnClickListener {
                currentOrder?.let {
                    onClick(it)
                }
            }
        }

        fun bind(order: Int) {
            currentOrder = order
        }
    }

    private var orders: List<Int> = listOf(1, 2, 3, 4, 5)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount() = orders.size

    fun setOrders(orders: List<Int>) {
        this.orders = orders
        notifyDataSetChanged()
    }

}