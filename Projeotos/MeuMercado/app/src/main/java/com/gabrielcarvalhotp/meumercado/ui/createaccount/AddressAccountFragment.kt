package com.gabrielcarvalhotp.meumercado.ui.createaccount

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gabrielcarvalhotp.meumercado.R
import com.gabrielcarvalhotp.meumercado.databinding.FragmentAddressAccountBinding
import com.gabrielcarvalhotp.meumercado.ui.MainActivity
import com.gabrielcarvalhotp.meumercado.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressAccountFragment : Fragment(), View.OnClickListener {
    private val viewModel: CreateAccountViewModel by viewModels<CreateAccountViewModel>()
    private var _binding: FragmentAddressAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddressAccountBinding.inflate(inflater, container, false)
        listeners()
        observe()
        return binding.root
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_create_account -> {
                handleCreateAccount()
            }

            R.id.text_have_account -> {
                viewModel.clearNewUserData()
                findNavController().navigate(R.id.action_addressAccountFragment_to_loginFragment)
            }
        }
    }

    private fun listeners() {
        binding.buttonCreateAccount.setOnClickListener(this)
        binding.textHaveAccount.setOnClickListener(this)
        binding.editCep.addTextChangedListener(object: TextWatcher {
            private var isUpdating = false
            private var oldText = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isUpdating) {
                    isUpdating = false
                    return
                }

                val newText = s.toString().filter { it.isDigit() }

                if (newText.length <= 5) {
                    oldText = newText
                }

                val formattedText = when {
                    newText.length <= 5 -> newText
                    newText.length <= 8 -> "${newText.substring(0, 5)}-${newText.substring(5)}"
                    else -> "${newText.substring(0, 5)}-${newText.substring(5, 8)}"
                }

                isUpdating = true
                binding.editCep.setText(formattedText)
                binding.editCep.setSelection(formattedText.length)
            }

            override fun afterTextChanged(s: Editable?) {
                val postalCode = binding.editCep.text.toString().filter { it.isDigit() }
                if (postalCode.length == 8) {
                    viewModel.getPostalCode(postalCode)
                }
            }
        } )
    }

    private fun observe() {
        viewModel.user.observe(viewLifecycleOwner) {
            if (it != null) {
                startActivity(Intent(context, MainActivity::class.java))
                requireActivity().finish()
            }
        }

        viewModel.userPostalCode.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.editAddress.setText(it.street)
                binding.editDistrict.setText(it.district)
                binding.editCity.setText(it.city)
                binding.editState.setText(it.state)
            }
        }
    }

    private fun handleCreateAccount() {
        val cep = binding.editCep.text.toString()
        val address = binding.editAddress.text.toString()
        val district = binding.editDistrict.text.toString()
        val city = binding.editCity.text.toString()
        val state = binding.editState.text.toString()
        viewModel.pushDataNewUser(cep, address, district, city, state)
        viewModel.createUser()
    }

}