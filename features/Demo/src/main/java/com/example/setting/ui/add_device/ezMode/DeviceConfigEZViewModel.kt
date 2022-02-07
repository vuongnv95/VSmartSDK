package com.example.setting.ui.add_device.ezMode

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import com.vht_iot.vsmartsdk.future.device.ConfigDeviceEzManager
import com.vht_iot.vsmartsdk.network.data.ResultApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeviceConfigEZViewModel @Inject constructor(
) : BaseViewModel() {

    val responseSuccess: MutableLiveData<ResultApi<String>> = MutableLiveData()
    val responseError: MutableLiveData<ResultApi<String>> = MutableLiveData()

    fun addDevice(context: Context, url: String) {
        isLoading.value = true
        ConfigDeviceEzManager.getInstance().getDeviceInfo(
            context,
            url,
            success = {
                isLoading.value = false
                responseSuccess.value = it
            },
            failt = {
                isLoading.value = false
                responseError.value = it
            })
    }
}