package com.gabrielcarvalhotp.meumercado.ui.cartadd

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gabrielcarvalhotp.meumercado.R
import com.gabrielcarvalhotp.meumercado.data.models.ProductModel
import com.gabrielcarvalhotp.meumercado.databinding.ActivityCartAddBinding
import com.gabrielcarvalhotp.meumercado.utils.Constants

class CartAddActivity : AppCompatActivity(), View.OnClickListener {
    private val viewModel: CartAddViewModel by viewModels()
    private lateinit var binding: ActivityCartAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        listeners()
    }

    override fun onResume() {
        super.onResume()
        bindDataProduct()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressedDispatcher.onBackPressed()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_cart_add -> {

            }
            R.id.image_add -> {
                val quantity = binding.textQuantity.text.toString().toInt()
                binding.textQuantity.text = "%02d".format(quantity + 1)
            }
            R.id.image_remove -> {
                val quantity = binding.textQuantity.text.toString().toInt()
                if (quantity == 1) return
                binding.textQuantity.text = "%02d".format(quantity - 1)
            }
        }
    }

    private fun listeners() {
        onBackPressedDispatcher.addCallback(this@CartAddActivity, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
        binding.buttonCartAdd.setOnClickListener(this)
        binding.imageAdd.setOnClickListener(this)
        binding.imageRemove.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun bindDataProduct() {
        val bundle = intent.extras ?: return
        val product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(Constants.EXTRAS.PRODUCT, ProductModel::class.java)
        } else {
            bundle.getParcelable(Constants.EXTRAS.PRODUCT)
        }
        binding.textName.text = product?.name
        binding.textPrice.text = "R$ %.2f".format(product?.price)
        binding.textDescription.text = product?.description
        binding.textQuantity.text = "01"
    }

}