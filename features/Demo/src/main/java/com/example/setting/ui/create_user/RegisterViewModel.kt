package com.example.setting.ui.create_user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.core.network.ApiInterface
import com.vht_iot.vsmartsdk.future.user_manager.UserManager
import com.vht_iot.vsmartsdk.network.data.ResultApi
import com.vht_iot.vsmartsdk.sdk_config.SDKConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val apiInterface: ApiInterface
) : BaseViewModel() {
    val statusRegister: MutableLiveData<ResultApi<String>> = MutableLiveData()

    fun handleRegisterPhone( identity: String, pass: String) {
        isLoading.value = true
        UserManager.getInstance().registerUserWithPhone(
            identity,
            pass,
            SDKConfig.sdkConfigData?.appId ?: "",
            sucess = {
                viewModelScope.launch {
                    isLoading.value = false
                    statusRegister.value = it
                }
            },
            failt = {
                viewModelScope.launch {
                    isLoading.value = false
                    statusRegister.value = it
                }
            })
    }

    fun handleRegisterEmail( identity: String, pass: String) {
        isLoading.value = true
        UserManager.getInstance().registerUserWithEmail(
            identity,
            pass,
            SDKConfig.sdkConfigData?.appId ?: "",
            sucess = {
                viewModelScope.launch {
                    isLoading.value = false
                    statusRegister.value = it
                }
            },
            failt = {
                viewModelScope.launch {
                    isLoading.value = false
                    statusRegister.value = it
                }
            })
    }
}
