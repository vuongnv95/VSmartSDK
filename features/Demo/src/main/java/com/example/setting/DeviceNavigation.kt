package com.example.setting

import android.os.Bundle
import com.example.core.navigationComponent.BaseNavigator

interface DeviceNavigation : BaseNavigator{
    fun openLoginToListDeviceScreen(bundle: Bundle? = null)
}