package com.example.setting.ui.list_func

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragment
import com.example.core.utils.toastMessage
import com.example.setting.HomeNavigation
import com.example.setting.R
import com.example.setting.databinding.FragmentListDeviceBinding
import com.example.utils.Defines
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFuncFragment : BaseFragment<FragmentListDeviceBinding, ListFuncViewModel>() {

    @Inject
    lateinit var appNavigation: HomeNavigation

    override val layoutId = R.layout.fragment_list_device

    private val viewViewModel: ListFuncViewModel by viewModels()
    override fun getVM() = viewViewModel


    val deviceAdapter: ListFuncAdapter by lazy {
        ListFuncAdapter({
            if (!isDoubleClick) {
                when (it.action) {
                    Defines.ACTION_HOME -> {
                        appNavigation.openListFuncToCreateHome()
                    }

                    Defines.ACTION_LIST_HOME -> {
                        appNavigation.openListFuncToListHome()
                    }
                }
            }
        })
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        binding.deviceRv.adapter = deviceAdapter
        viewViewModel.loadDataDevice()
    }

    override fun setOnClick() {
        super.setOnClick()
        binding.settingIv.setOnClickListener {
            if (!isDoubleClick) {
            }
        }
        binding.tvLogout.setOnClickListener {
            viewViewModel.logout()
        }
    }

    override fun bindingAction() {
        super.bindingAction()
        viewViewModel.funcListResponse.observe(this) {
            deviceAdapter.funcDatas = it
        }

        viewViewModel.logoutResponse.observe(this) {
            appNavigation.openListFuncToLogin()
//            if (it){
//                appNavigation.openListFuncToLogin()
//            }else{
//                toastMessage("Có lỗi xảy ra")
//            }
        }
    }
}