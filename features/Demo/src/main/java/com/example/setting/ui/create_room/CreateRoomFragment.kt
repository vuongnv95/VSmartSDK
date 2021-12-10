package com.example.setting.ui.create_home

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragment
import com.example.core.utils.toastMessage
import com.example.setting.DeviceNavigation
import com.example.setting.R
import com.example.setting.databinding.FragmentCreateRoomBinding
import com.example.utils.Defines
import com.vht_iot.vsmartsdk.network.data.VOrganizationResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateRoomFragment : BaseFragment<FragmentCreateRoomBinding, CreateRoomViewModel>() {

    private var vOrganizationResponse: VOrganizationResponse? = null

    @Inject
    lateinit var appNavigation: DeviceNavigation

    override val layoutId = R.layout.fragment_create_room

    private val viewModel: CreateRoomViewModel by viewModels()
    override fun getVM() = viewModel

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        arguments?.let {
            vOrganizationResponse =
                it.getSerializable(Defines.BUNDLE_ITEM_HOME) as VOrganizationResponse
        }
    }

    override fun setOnClick() {
        super.setOnClick()
        binding.btnDone.setOnClickListener {
            if (!isDoubleClick) {
                if (binding.etHomeName.text.isNullOrEmpty()) {
                    toastMessage("Kiểm tra lại thông tin")
                    return@setOnClickListener
                }
                viewModel.createRoom(
                    vOrganizationResponse,
                    binding.etHomeName.text.toString().trim(),
                    binding.etDescription.text.toString().trim()
                )
            }
        }
    }

    override fun bindingStateView() {
        super.bindingStateView()
        viewModel.responseOrganization.observe(viewLifecycleOwner) {
            toastMessage("Tạo phòng thành công")
            appNavigation.navigateUp()
        }

        viewModel.responseEror.observe(viewLifecycleOwner) {
            toastMessage(it.exception ?: "")
        }
    }
}