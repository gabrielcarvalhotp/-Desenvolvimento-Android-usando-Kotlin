package com.gabrielcarvalhotp.gastoviagem

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import com.gabrielcarvalhotp.gastoviagem.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonCalculate.setOnClickListener { calculate() }
    }

    private fun calculate() {
        try {
            val distance = binding.editDistance.text.toString().toDouble()
            val price = binding.editPrice.text.toString().toDouble()
            val autonomy = binding.editAutonomy.text.toString().toDouble()
            if (autonomy == 0.0)
                throw IllegalArgumentException(R.string.validade_impossible_to_divide.toString())

            binding.textTotalValue.text = String.format("R$ %.2f", (distance * price) / autonomy)
        } catch (e: NumberFormatException) {
            Toast.makeText(this, R.string.validade_fill_all_fields, Toast.LENGTH_LONG).show()
        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }
}