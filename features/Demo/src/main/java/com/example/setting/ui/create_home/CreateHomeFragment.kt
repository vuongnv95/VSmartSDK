package com.example.setting.ui.create_home

import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragment
import com.example.core.utils.toastMessage
import com.example.setting.DeviceNavigation
import com.example.setting.R
import com.example.setting.databinding.FragmentCreateHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateHomeFragment : BaseFragment<FragmentCreateHomeBinding, CreateHomeViewModel>() {

    @Inject
    lateinit var appNavigation: DeviceNavigation

    override val layoutId = R.layout.fragment_create_home

    private val viewModel: CreateHomeViewModel by viewModels()
    override fun getVM() = viewModel

    override fun setOnClick() {
        super.setOnClick()
        binding.btnDone.setOnClickListener {
            if (!isDoubleClick) {
                if (binding.etHomeName.text.isNullOrEmpty()) {
                    toastMessage("Kiểm tra lại thông tin")
                    return@setOnClickListener
                }
                viewModel.createHome(
                    binding.etHomeName.text.toString().trim(),
                    binding.etAddress.text.toString().trim()
                )
            }
        }
    }

    override fun bindingStateView() {
        super.bindingStateView()
        viewModel.responseOrganization.observe(viewLifecycleOwner) {
            toastMessage("Tạo nhà thành công")
            appNavigation.navigateUp()
        }

        viewModel.responseEror.observe(viewLifecycleOwner) {
            toastMessage(it.exception ?: "")
        }
    }
}