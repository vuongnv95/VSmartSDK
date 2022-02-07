package com.example.setting.ui.add_device.apMode

import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragment
import com.example.core.utils.toastMessage
import com.example.setting.DeviceNavigation
import com.example.setting.R
import com.example.setting.databinding.FragmentDeviceConfigAPBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DeviceConfigAPFragment :
    BaseFragment<FragmentDeviceConfigAPBinding, ConfigDeviceAPViewModel>() {

    @Inject
    lateinit var appNavigation: DeviceNavigation
    override val layoutId = R.layout.fragment_device_config_a_p

    private val viewModel: ConfigDeviceAPViewModel by viewModels()
    override fun getVM() = viewModel

    override fun setOnClick() {
        super.setOnClick()
        binding.btnDone.setOnClickListener {
            if (!isDoubleClick) {
                if (binding.etSsid.text.isNullOrEmpty()) {
                    toastMessage("Nhập ssid")
                    return@setOnClickListener
                }
                if (binding.etPassword.text.isNullOrEmpty()) {
                    toastMessage("Nhập password")
                    return@setOnClickListener
                }
                context?.let { it1 ->
                    viewModel.addDevice(
                        it1,
                        binding.etSsid.text.toString(),
                        binding.etPassword.text.toString()
                    )
                }
            }
        }
    }

    override fun bindingStateView() {
        super.bindingStateView()
        viewModel.responseSuccess.observe(viewLifecycleOwner) {
            toastMessage("Tạo thiết bị thành công")
            appNavigation.navigateUp()
        }

        viewModel.responseError.observe(viewLifecycleOwner) {
            toastMessage(it.exception ?: "")
        }
    }
}