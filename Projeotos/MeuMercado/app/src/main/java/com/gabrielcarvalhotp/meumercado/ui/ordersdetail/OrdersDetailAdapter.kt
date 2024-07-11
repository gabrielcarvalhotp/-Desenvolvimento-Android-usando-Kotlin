package com.gabrielcarvalhotp.meumercado.ui.ordersdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcarvalhotp.meumercado.databinding.ProductCartItemBinding

class OrdersDetailAdapter(
    context: Context
):RecyclerView.Adapter<OrdersDetailAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: ProductCartItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {

        }

    }

    private var items = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ProductCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}