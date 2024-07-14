package com.gabrielcarvalhotp.meumercado.ui.createaccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gabrielcarvalhotp.meumercado.R
import com.gabrielcarvalhotp.meumercado.databinding.FragmentCreateAccountBinding
import com.gabrielcarvalhotp.meumercado.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountFragment : Fragment(), View.OnClickListener {
    private val viewModel: CreateAccountViewModel by viewModels<CreateAccountViewModel>()
    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        listeners()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.editName.text?.clear()
        binding.editEmail.text?.clear()
        binding.editPassword.text?.clear()
    }

    private fun listeners() {
        binding.textHaveAccount.setOnClickListener(this)
        binding.buttonAccess.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.text_have_account -> {
                viewModel.clearNewUserData()
                findNavController().navigate(R.id.action_createAccountFragment_to_loginFragment)
            }
            R.id.button_access -> {
                val name = binding.editName.text.toString()
                val email = binding.editEmail.text.toString()
                val password = binding.editPassword.text.toString()
                viewModel.pushDataNewUser(name, email, password)
                findNavController().navigate(R.id.action_createAccountFragment_to_addressAccountFragment)
            }
        }
    }
}