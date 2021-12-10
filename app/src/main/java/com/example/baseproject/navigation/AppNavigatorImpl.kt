package com.example.baseproject.navigation

import android.os.Bundle
import com.example.baseproject.R
import com.example.core.navigationComponent.BaseNavigatorImpl
import com.example.setting.DeviceNavigation
import com.example.setting.HomeNavigation
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class AppNavigatorImpl @Inject constructor() : BaseNavigatorImpl(),
    AppNavigation, DeviceNavigation,HomeNavigation {

    override fun openSplashToHomeScreen(bundle: Bundle?) {
        openScreen(R.id.action_splashFragment_to_loginFragment, bundle)
    }

    override fun openSplashToLoginScreen(bundle: Bundle?) {
        openScreen(R.id.action_homeFragment_to_loginFragment, bundle)
    }

    override fun openLoginToListDeviceScreen(bundle: Bundle?) {
        openScreen(R.id.action_loginFragment_to_home_navigation, bundle)
    }

    override fun openListDeviceToStatisticScreen(bundle: Bundle?) {
        openScreen(R.id.action_listDeviceFragment_to_statisticDeviceFragment, bundle)
    }

    override fun openLoginToRegisterScreen(bundle: Bundle?) {
        openScreen(R.id.action_loginFragment_to_user_navigation, bundle)
    }

    override fun openListDeviceSettingFragment(bundle: Bundle?) {
        openScreen(R.id.action_listDeveice_to_settingFragment, bundle)
    }

    override fun openLoginToForgetPasswordScreen(bundle: Bundle?) {
        openScreen(R.id.action_loginFragment_to_forgetPasswordFragment, bundle)
    }

    override fun openListFuncToCreateHome(bundle: Bundle?) {
        openScreen(R.id.action_listFuncFragment_to_createHomeFragment, bundle)
    }

    override fun openListFuncToListHome(bundle: Bundle?) {
        openScreen(R.id.action_listFuncFragment_to_listHomeFragment, bundle)
    }

    override fun openListHomeToListRoomFragment(bundle: Bundle?) {
        openScreen(R.id.action_listHomeFragment_to_listRoomFragment, bundle)
    }

    override fun openListRoomToCreateRoomFragment(bundle: Bundle?) {
        openScreen(R.id.action_listRoomFragment_to_createRoomFragment, bundle)
    }

    override fun openListFuncToLogin(bundle: Bundle?) {
        openScreen(R.id.action_listRoomFragment_to_createRoomFragment, bundle)
    }

}