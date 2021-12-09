package com.example.setting

import android.os.Bundle
import com.example.core.navigationComponent.BaseNavigator

interface DeviceNavigation : BaseNavigator{
    fun openLoginToListDeviceScreen(bundle: Bundle? = null)
    fun openListDeviceToStatisticScreen(bundle: Bundle? = null)
    fun openLoginToRegisterScreen(bundle: Bundle? = null)
    fun openListDeviceSettingFragment(bundle: Bundle? = null)
    fun openLoginToForgetPasswordScreen(bundle: Bundle? = null)
}