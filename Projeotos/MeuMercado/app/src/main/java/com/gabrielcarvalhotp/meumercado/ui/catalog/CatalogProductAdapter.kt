package com.gabrielcarvalhotp.meumercado.ui.catalog

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcarvalhotp.meumercado.R
import com.gabrielcarvalhotp.meumercado.data.models.ProductModel
import com.gabrielcarvalhotp.meumercado.databinding.ProductCatalogItemBinding

class CatalogProductAdapter(
    private val context: Context,
    private val onClick: (ProductModel) -> Unit
): RecyclerView.Adapter<CatalogProductAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: ProductCatalogItemBinding, val onClick: (ProductModel) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        private var currentProduct : ProductModel? = null
        init {
            binding.root.setOnClickListener {
                onClick(currentProduct ?: return@setOnClickListener)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(product: ProductModel) {
            currentProduct = product
            binding.textDescription.text = product.name
            binding.textPrice.text = "R$ %.2f".format(product.price)
            binding.textWeight.text = "${product.weight}${product.unit}"
        }
    }

    private var products: List<ProductModel> = listOf(
        ProductModel(0, "Café três corações extra forte", context.getString(R.string.description_product_example), 15.0, "g", 500.0),
        ProductModel(0, "Café três corações extra forte", context.getString(R.string.description_product_example), 15.0, "g", 500.0),
        ProductModel(0, "Café três corações extra forte", context.getString(R.string.description_product_example), 15.0, "g", 500.0),
        ProductModel(0, "Café três corações extra forte", context.getString(R.string.description_product_example), 15.0, "g", 500.0),
        ProductModel(0, "Café três corações extra forte", context.getString(R.string.description_product_example), 15.0, "g", 500.0),
        ProductModel(0, "Café três corações extra forte", context.getString(R.string.description_product_example), 15.0, "g", 500.0),
        ProductModel(0, "Café três corações extra forte", context.getString(R.string.description_product_example), 15.0, "g", 500.0),
        ProductModel(0, "Café três corações extra forte", context.getString(R.string.description_product_example), 15.0, "g", 500.0),
        ProductModel(0, "Café três corações extra forte", context.getString(R.string.description_product_example), 15.0, "g", 500.0),
        ProductModel(0, "Café três corações extra forte", context.getString(R.string.description_product_example), 15.0, "g", 500.0),
        ProductModel(0, "Café três corações extra forte", context.getString(R.string.description_product_example), 15.0, "g", 500.0)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = ProductCatalogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(item, onClick)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = products[position]
        holder.bind(item)
    }

    fun setProducts(products: List<ProductModel>) {
        this.products = products
        notifyDataSetChanged()
    }
}