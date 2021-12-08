package com.example.setting.ui.create_user

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragment
import com.example.setting.DeviceNavigation
import com.example.setting.R
import com.example.setting.databinding.FragmentRegisterBinding
import com.vht_iot.vsmartsdk.network.data.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.regex.Pattern
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    @Inject
    lateinit var appNavigation: DeviceNavigation

    override val layoutId = R.layout.fragment_register

    private val viewModel: RegisterViewModel by viewModels()
    override fun getVM() = viewModel

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

    }

    override fun setOnClick() {
        super.setOnClick()
        binding.registerBtn.setOnClickListener {
            if (isDoubleClick) {
                return@setOnClickListener
            }
            val identity = binding.inputEmailEdt.text?.trim().toString()
            val pass = binding.inputPassEdt.text?.trim().toString()
            if (identity.isEmpty() || pass.isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "Kiểm tra lại thông tin đăng kí",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (Patterns.PHONE.matcher(identity).matches()) {
                viewModel.handleRegisterPhone(
                    identity,
                    pass
                )
            } else if (Patterns.EMAIL_ADDRESS.matcher(identity).matches()) {
                viewModel.handleRegisterEmail(
                    identity,
                    pass
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    "Kiểm tra lại thông tin đăng kí",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    override fun bindingStateView() {
        super.bindingStateView()
        viewModel.statusRegister.observe(this) {
            if (it.status == Status.SUCCESS) {
                Toast.makeText(
                    requireContext(),
                    "Đăng kí thành công!",
                    Toast.LENGTH_SHORT
                ).show()
                appNavigation.navigateUp()
            } else {
                Toast.makeText(
                    requireContext(),
                    it.exception,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}