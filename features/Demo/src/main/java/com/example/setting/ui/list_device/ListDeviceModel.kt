package com.example.setting.ui.list_device

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.vht_iot.vsmartsdk.future.device.DeviceManager
import com.vht_iot.vsmartsdk.network.data.DeviceResponse
import com.vht_iot.vsmartsdk.network.data.ResultApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListDeviceModel @Inject constructor(
) : BaseViewModel() {

    val deviceListResponse: MutableLiveData<ResultApi<DeviceResponse>> = MutableLiveData()

    fun loadDataDevice() {

        DeviceManager.getInstance().getListDevice(sucess = {
            viewModelScope.launch {
                deviceListResponse.value = it
            }
        }, failt = {

        })

    }
}
