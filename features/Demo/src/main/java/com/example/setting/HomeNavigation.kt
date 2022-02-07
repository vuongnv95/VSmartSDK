package com.example.setting

import android.os.Bundle
import com.example.core.navigationComponent.BaseNavigator

interface HomeNavigation : BaseNavigator {
    fun openListFuncToCreateHome(bundle: Bundle? = null)
    fun openListFuncToListHome(bundle: Bundle? = null)
    fun openListHomeToListRoomFragment(bundle: Bundle? = null)
    fun openListRoomToCreateRoomFragment(bundle: Bundle? = null)
    fun openListFuncToLogin(bundle: Bundle? = null)
    fun openListFuncToEZModeDevice(bundle: Bundle? = null)
    fun openListFuncToAPModeDevice(bundle: Bundle? = null)
}