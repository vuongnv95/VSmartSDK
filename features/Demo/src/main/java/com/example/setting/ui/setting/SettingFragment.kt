package com.example.setting.ui.setting

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragment
import com.example.setting.DeviceNavigation
import com.example.setting.R
import com.example.setting.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>(), CoroutineScope {

    @Inject
    lateinit var appNavigation: DeviceNavigation

    override val layoutId = R.layout.fragment_setting

    private val viewModel: SettingViewModel by viewModels()
    override fun getVM() = viewModel

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + SupervisorJob()

    val settingAdapter: SettingAdapter by lazy {
        SettingAdapter({
            if (!isDoubleClick) {
                appNavigation.openLoginToRegisterScreen()
            }
        })
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        binding.settingRv.adapter = settingAdapter
    }

    override fun bindingStateView() {
        super.bindingStateView()
        viewModel.dataSetting.observe(this) {
            settingAdapter.settingDatas = it
        }
        viewModel.getDataSetting()
    }
}