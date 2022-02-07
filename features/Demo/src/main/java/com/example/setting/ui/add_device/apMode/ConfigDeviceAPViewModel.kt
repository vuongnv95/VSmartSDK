package com.example.setting.ui.add_device.apMode

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import com.vht_iot.vsmartsdk.future.device.ConfigDeviceApManager
import com.vht_iot.vsmartsdk.network.data.ResultApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfigDeviceAPViewModel @Inject constructor(
) : BaseViewModel() {
    val responseSuccess: MutableLiveData<ResultApi<String>> = MutableLiveData()
    val responseError: MutableLiveData<ResultApi<String>> = MutableLiveData()

    fun addDevice(context: Context, ssid: String, password: String) {
        isLoading.value = true
        ConfigDeviceApManager.getInstance().sendRequestCompatible(
            context,
            ssid,
            password,
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