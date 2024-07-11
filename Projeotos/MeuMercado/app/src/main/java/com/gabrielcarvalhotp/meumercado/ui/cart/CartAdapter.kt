package com.gabrielcarvalhotp.meumercado.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcarvalhotp.meumercado.databinding.ProductCartItemBinding

class CartAdapter(context: Context): RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: ProductCartItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(product: String) {

        }
    }

    private var items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ProductCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    fun setItems(items: List<String>) {
        this.items = items
        notifyDataSetChanged()
    }
}