package com.example.setting.ui.list_func

import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import com.example.utils.Defines
import com.vht_iot.vsmartsdk.future.user_manager.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListFuncViewModel @Inject constructor(
) : BaseViewModel() {

    val funcListResponse: MutableLiveData<List<FuncData>> = MutableLiveData()
    val logoutResponse: MutableLiveData<Boolean> = MutableLiveData()

    fun loadDataDevice() {
        var listFunc = mutableListOf<FuncData>()
        listFunc.add(FuncData(0, 1, "Quản lý nhà", ""))
        listFunc.add(FuncData(1, Defines.ACTION_HOME, "Thêm nhà", ""))
        listFunc.add(FuncData(1, Defines.ACTION_LIST_HOME, "Danh sách nhà", ""))

        listFunc.add(FuncData(0, 1, "Quản lý thiết bị", ""))
        listFunc.add(FuncData(1, 1, "Thêm thiết bị", ""))
        listFunc.add(FuncData(1, 1, "Danh sách thiết bị", ""))
        funcListResponse.value = listFunc
    }

    fun logout() {
        UserManager.getInstance().logout(sucess = {
            logoutResponse.value = true
        }, failt = {
            logoutResponse.value = false
        })
    }
}
