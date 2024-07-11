package com.gabrielcarvalhotp.meumercado.ui.catalog

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gabrielcarvalhotp.meumercado.R
import com.gabrielcarvalhotp.meumercado.data.models.EstablishmentsModel
import com.gabrielcarvalhotp.meumercado.data.models.ProductModel
import com.gabrielcarvalhotp.meumercado.databinding.ActivityCatalogBinding
import com.gabrielcarvalhotp.meumercado.ui.cartadd.CartAddActivity
import com.gabrielcarvalhotp.meumercado.utils.Constants

class CatalogActivity : AppCompatActivity() {
    private val viewModel: CatalogViewModel by viewModels()
    private lateinit var binding: ActivityCatalogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        binding.rvCategories.adapter = CatalogCategoryAdapter { handleCategoryItemClick(it) }
        binding.rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvProducts.adapter = CatalogProductAdapter(this) { handleProductItemClick(it) }
        binding.rvProducts.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        listeners()

    }

    override fun onResume() {
        super.onResume()
        bindDataEstablishments()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressedDispatcher.onBackPressed()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    private fun listeners() {
        onBackPressedDispatcher.addCallback(this@CatalogActivity, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun bindDataEstablishments() {
        val bundle = intent.extras ?: return
        val establishment = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(Constants.EXTRAS.ESTABLISHMENT, EstablishmentsModel::class.java)
        } else {
            bundle.getParcelable(Constants.EXTRAS.ESTABLISHMENT)
        }
        supportActionBar?.title = establishment?.name
        binding.textAddress.text = establishment?.address
        binding.textDeliveryTax.text = "Taxa de entrega: ${String.format("%.2f", establishment?.deliveryTax ?: 0.0)}"
        binding.textMinimumPurchase.text = "Compra mínima: ${String.format("%.2f", establishment?.minimumPurchase ?: 0.0)}"
    }

    private fun handleCategoryItemClick(category: String) {

    }

    private fun handleProductItemClick(product: ProductModel) {
        val intent = Intent(this, CartAddActivity::class.java)
        intent.putExtra(Constants.EXTRAS.PRODUCT, product)
        startActivity(intent)
    }

}