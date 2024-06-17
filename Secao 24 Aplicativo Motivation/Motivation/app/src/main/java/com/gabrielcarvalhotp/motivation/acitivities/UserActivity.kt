package com.gabrielcarvalhotp.motivation.acitivities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gabrielcarvalhotp.motivation.R
import com.gabrielcarvalhotp.motivation.databinding.ActivityUserBinding
import com.gabrielcarvalhotp.motivation.utils.MotivationContants
import com.gabrielcarvalhotp.motivation.utils.SecurityPreferences

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.buttonSave.setOnClickListener { buttonSaveClick() }
        verifyUserName()
    }

    private fun verifyUserName() {
        val userName = SecurityPreferences(this).getString(MotivationContants.KEY.USER_NAME)
        if (userName != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun buttonSaveClick() {
        val name = binding.editYourName.text.toString()
        if (name == "") {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
            return
        }
        SecurityPreferences(this).storeString(MotivationContants.KEY.USER_NAME, name)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}