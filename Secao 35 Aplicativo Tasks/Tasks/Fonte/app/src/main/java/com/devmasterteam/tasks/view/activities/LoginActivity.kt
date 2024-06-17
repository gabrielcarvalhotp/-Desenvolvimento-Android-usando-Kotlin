package com.devmasterteam.tasks.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.devmasterteam.tasks.databinding.ActivityLoginBinding
import com.devmasterteam.tasks.viewmodel.LoginViewModel
import com.devmasterteam.tasks.viewmodel.PriorityViewModel
import java.time.Instant

class LoginActivity : AppCompatActivity() {

    companion object {
        var email: String = ""
        var password: String = ""
    }


    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding = ActivityLoginBinding.inflate(layoutInflater)



        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener { handleLogin() }
        binding.textRegister.setOnClickListener { handleRegister() }
        observe()
        viewModel.verifyLoggedUser()
    }

    private fun observe() {
        viewModel.personModel.observe(this) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

        viewModel.responseModel.observe(this) {
            if (!it.status()) {
                Toast.makeText(this, it.message(), Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.loggedUser.observe(this) {
            val priorityViewModel = ViewModelProvider(this).get(PriorityViewModel::class.java)
            if (it) {
                priorityViewModel.loadPriorities()
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            } else {
                priorityViewModel.saveAll()
            }
        }
    }

    private fun handleLogin() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()
        viewModel.doLogin(email, password)
    }

    private fun handleRegister() {
        startActivity(Intent(applicationContext, RegisterActivity::class.java))
        viewModel.verifyLoggedUser()
    }
}