package com.example.setting.ui.create_user.forget_pass

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.core.network.ApiInterface
import com.vht_iot.vsmartsdk.future.user_manager.UserManager
import com.vht_iot.vsmartsdk.network.data.ResultApi
import com.vht_iot.vsmartsdk.sdk_config.SDKConfig
import com.vht_iot.vsmartsdk.utils.VDefine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val apiInterface: ApiInterface
) : BaseViewModel() {
    val statusRegister: MutableLiveData<ResultApi<String>> = MutableLiveData()
    val registerCode: MutableLiveData<ResultApi<String>> = MutableLiveData()

    fun sendVerificationCode(identity: String) {
        isLoading.value = true
        UserManager.getInstance().sendVerificationCode(
            identity,
           VDefine.OTPType.RESET_PASSWORD,
            sucess = {
                viewModelScope.launch {
                    isLoading.value = false
                    registerCode.value = it
                }
            },
            failt = {
                viewModelScope.launch {
                    isLoading.value = false
                    registerCode.value = it
                }
            })
    }

    fun handleChangePassword(identity: String, pass: String, passCode: String) {
        isLoading.value = true
        UserManager.getInstance().register(
            identity,
            pass,
            passCode,
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
        VDefine.useAddminToken = true
        UserManager.getInstance().registerUserWithEmail(
            identity,
            pass,
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
