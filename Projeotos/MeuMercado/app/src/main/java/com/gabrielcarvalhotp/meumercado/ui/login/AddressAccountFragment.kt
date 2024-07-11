package com.gabrielcarvalhotp.meumercado.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gabrielcarvalhotp.meumercado.R
import com.gabrielcarvalhotp.meumercado.databinding.FragmentAddressAccountBinding
import com.gabrielcarvalhotp.meumercado.ui.MainActivity

class AddressAccountFragment : Fragment(), View.OnClickListener {
    private val viewModel: LoginViewModel by viewModels<LoginViewModel>()
    private var _binding: FragmentAddressAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddressAccountBinding.inflate(inflater, container, false)
        listeners()
        return binding.root
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_create_account -> { handleCreateAccount() }
            R.id.text_have_account -> {
                findNavController().navigate(R.id.action_addressAccountFragment_to_loginFragment)
            }
        }
    }

    private fun listeners() {
        binding.buttonCreateAccount.setOnClickListener(this)
        binding.textHaveAccount.setOnClickListener(this)
    }

    private fun handleCreateAccount() {
        startActivity(Intent(context, MainActivity::class.java))
    }

}