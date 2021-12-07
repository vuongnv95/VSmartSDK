package com.example.baseproject.ui.splash

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentSplashBinding
import com.example.baseproject.navigation.AppNavigation
import com.example.core.base.BaseFragment
import com.example.core.utils.setTextCompute
import com.example.setting.model.Ahihi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    @Inject
    lateinit var appNavigation: AppNavigation

    @Inject
    lateinit var ahihi: Ahihi

    override val layoutId = R.layout.fragment_splash

    private val viewModel: SplashViewModel by viewModels()

    override fun getVM() = viewModel

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        binding.text.setTextCompute("Splash")
        ahihi.a = 7
    }

    override fun bindingAction() {
        super.bindingAction()
        viewModel.actionSPlash.observe(viewLifecycleOwner, {
            when (it) {
                SplashActionState.Finish -> {
                    appNavigation.openSplashToHomeScreen()
                }
            }
        })
    }

}