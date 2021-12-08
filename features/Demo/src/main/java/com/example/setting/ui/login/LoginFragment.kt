package com.example.setting.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragment
import com.example.core.utils.prefetcher.PrefetchRecycledViewPool
import com.example.setting.DeviceNavigation
import com.example.setting.R
import com.example.setting.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(), CoroutineScope {

    @Inject
    lateinit var appNavigation: DeviceNavigation

    override val layoutId = R.layout.fragment_login

    private val viewModel: LoginViewModel by viewModels()
    override fun getVM() = viewModel

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + SupervisorJob()

    private val viewPool: PrefetchRecycledViewPool by lazy {
        PrefetchRecycledViewPool(
            requireActivity(),
            this
        ).apply {
            prepare()
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

    }

    override fun setOnClick() {
        super.setOnClick()
        binding.loginBtn.setOnClickListener {
            if (binding.inputPassEdt.text?.trim()
                    .isNullOrEmpty() || binding.inputEmailEdt.text?.trim().isNullOrEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "Kiểm tra lại thông tin đăng nhập",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            viewModel.handleLogin(
                requireContext(),
                binding.inputEmailEdt.text.toString(),
                binding.inputPassEdt.text.toString()
            )
        }

        binding.registerTv.setOnClickListener {
            if (!isDoubleClick) {
                appNavigation.openSettingToRegisterScreen()
            }
        }
    }

    override fun bindingStateView() {
        super.bindingStateView()
        viewModel.statusLogin.observe(this) {
            if (it) {
                appNavigation.openLoginToListDeviceScreen()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Có lỗi xảy ra.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}