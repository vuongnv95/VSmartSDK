package com.example.baseproject.navigation

import android.os.Bundle
import com.example.baseproject.R
import com.example.core.navigationComponent.BaseNavigatorImpl
import com.example.setting.DeviceNavigation
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class AppNavigatorImpl @Inject constructor() : BaseNavigatorImpl(),
    AppNavigation, DeviceNavigation {

    override fun openSplashToHomeScreen(bundle: Bundle?) {
        openScreen(R.id.action_splashFragment_to_loginFragment, bundle)
    }

    override fun openSplashToLoginScreen(bundle: Bundle?) {
        openScreen(R.id.action_homeFragment_to_loginFragment, bundle)
    }

    override fun openLoginToListDeviceScreen(bundle: Bundle?) {
        openScreen(R.id.action_loginFragment_to_device_navigation, bundle)
    }

    override fun openListDeviceToStatisticScreen(bundle: Bundle?) {
        openScreen(R.id.action_listDeviceFragment_to_statisticDeviceFragment, bundle)
    }

    override fun openSettingToRegisterScreen(bundle: Bundle?) {
        openScreen(R.id.action_settingFragment_to_user_navigation, bundle)
    }

    override fun openListDeviceSettingFragment(bundle: Bundle?) {
        openScreen(R.id.action_listDeveice_to_settingFragment, bundle)
    }

}