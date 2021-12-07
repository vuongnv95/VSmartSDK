package com.example.setting.ui.list_device

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragment
import com.example.setting.DeviceNavigation
import com.example.setting.R
import com.example.setting.databinding.FragmentListDeviceBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListDeviceFragment : BaseFragment<FragmentListDeviceBinding, ListDeviceModel>() {

    @Inject
    lateinit var appNavigation: DeviceNavigation

    override val layoutId = R.layout.fragment_list_device

    private val viewModel: ListDeviceModel by viewModels()
    override fun getVM() = viewModel


    val deviceAdapter: DeviceAdapter by lazy {
        DeviceAdapter({
            if (!isDoubleClick) {

            }
        })
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        binding.deviceRv.adapter = deviceAdapter
        viewModel.loadDataDevice()
    }

    override fun bindingAction() {
        super.bindingAction()
        viewModel.deviceListResponse.observe(this) {
            deviceAdapter.devices = it.data?.devices ?: mutableListOf()
        }
    }
}