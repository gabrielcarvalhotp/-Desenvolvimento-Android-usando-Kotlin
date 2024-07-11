package com.gabrielcarvalhotp.meumercado.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gabrielcarvalhotp.meumercado.R
import com.gabrielcarvalhotp.meumercado.databinding.FragmentCreateAccountBinding

class CreateAccountFragment : Fragment(), View.OnClickListener {
    private val viewModel: LoginViewModel by viewModels<LoginViewModel>()
    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        listeners()
        return binding.root
    }

    private fun listeners() {
        binding.textHaveAccount.setOnClickListener(this)
        binding.buttonAccess.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.text_have_account -> {
                findNavController().navigate(R.id.action_createAccountFragment_to_loginFragment)
            }
            R.id.button_access -> {
                findNavController().navigate(R.id.action_createAccountFragment_to_addressAccountFragment)
            }
        }
    }
}