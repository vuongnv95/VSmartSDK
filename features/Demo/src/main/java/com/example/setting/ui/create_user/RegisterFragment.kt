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
            val code = binding.inputPasscodeEdt.text?.trim().toString()
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
                if (code.isEmpty()){
                    Toast.makeText(
                        requireContext(),
                        "Vui lòng lấy thông tin mã code.",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                viewModel.handleRegisterWithPhone(
                    identity,
                    pass,
                    code
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


        binding.passCodeBtn.setOnClickListener {
            val identity = binding.inputEmailEdt.text?.trim().toString()
            if (!isDoubleClick) {
                if (!Patterns.PHONE.matcher(identity).matches()) {
                    Toast.makeText(
                        requireContext(),
                        "Chức năng này chỉ dành cho đăng kí bằng số điện thoại",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                viewModel.sendVerificationCode(
                    identity
                )
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

        viewModel.registerCode.observe(this) {
            if (it.status == Status.SUCCESS) {
                Toast.makeText(
                    requireContext(),
                    "Gửi mã thành công!",
                    Toast.LENGTH_SHORT
                ).show()
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