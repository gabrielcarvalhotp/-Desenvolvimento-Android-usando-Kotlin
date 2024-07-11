package com.gabrielcarvalhotp.meumercado.ui.catalog

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcarvalhotp.meumercado.R
import com.gabrielcarvalhotp.meumercado.databinding.CategoryItemBinding

class CatalogCategoryAdapter(
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<CatalogCategoryAdapter.MyViewHolder>() {

    private var selectedPosition = 0

    inner class MyViewHolder(private val binding: CategoryItemBinding, val onClick: (String) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        private var currentCategory: String? = null
        init {
            binding.root.setOnClickListener {
                selectedPosition = adapterPosition
                onClick(currentCategory ?: "")
                notifyDataSetChanged()
            }
        }
        fun bind(category: String, position: Int) {
            currentCategory = category
            binding.textDescription.text = category
            if (selectedPosition == position) {
                binding.textDescription.background = AppCompatResources.getDrawable(binding.root.context, R.drawable.shape_rounded_color_primary)
                binding.textDescription.setTextColor(Color.WHITE)
            } else {
                binding.textDescription.background = AppCompatResources.getDrawable(binding.root.context, R.drawable.shape_rounded_darker_gray)
                binding.textDescription.setTextColor(Color.BLACK)
            }
        }
    }

    private var categories: List<String> = listOf(
        "Supermercado", "Padaria", "Hortifruíto", "Lanchonete", "Restaurante"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(item, onClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(categories[position], position)
    }

    override fun getItemCount(): Int = categories.size

    fun setCategories(categories: List<String>) {
        this.categories = categories
        notifyDataSetChanged()
    }
}
