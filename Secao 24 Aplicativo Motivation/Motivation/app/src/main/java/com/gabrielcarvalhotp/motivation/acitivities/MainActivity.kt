package com.gabrielcarvalhotp.motivation.acitivities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gabrielcarvalhotp.motivation.R
import com.gabrielcarvalhotp.motivation.databinding.ActivityMainBinding
import com.gabrielcarvalhotp.motivation.enuns.CategoryPhase
import com.gabrielcarvalhotp.motivation.repositories.Mock
import com.gabrielcarvalhotp.motivation.utils.MotivationContants
import com.gabrielcarvalhotp.motivation.utils.SecurityPreferences

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryPhase: CategoryPhase
    private val mock = Mock()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple)
        handleUserName()
        binding.buttonNewPhrase.setOnClickListener { buttonNewPhraseClick() }
        binding.imageAll.setOnClickListener { handleFilter(it) }
        binding.imageHappy.setOnClickListener { handleFilter(it) }
        binding.imageSunny.setOnClickListener { handleFilter(it) }
        handleFilter(binding.imageAll)
    }

    @SuppressLint("SetTextI18n")
    private fun handleUserName() {
        val userName = SecurityPreferences(this).getString(MotivationContants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $userName!"
    }

    private fun buttonNewPhraseClick() {
        binding.textMessage.text = mock.getPhrase(categoryPhase)
    }

    private fun handleFilter(view: View) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        (view as ImageView).setColorFilter(ContextCompat.getColor(this, R.color.white))

        when (view) {
            binding.imageAll -> categoryPhase = CategoryPhase.ALL
            binding.imageHappy -> categoryPhase = CategoryPhase.HAPPY
            binding.imageSunny -> categoryPhase = CategoryPhase.SUNNY
        }
        buttonNewPhraseClick()
    }
}