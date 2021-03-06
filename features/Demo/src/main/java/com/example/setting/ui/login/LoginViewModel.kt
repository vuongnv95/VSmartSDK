package com.example.setting.ui.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import com.example.core.network.ApiInterface
import com.vht_iot.vsmartsdk.future.group_manager.GroupManager
import com.vht_iot.vsmartsdk.future.user_manager.UserManager
import com.vht_iot.vsmartsdk.network.data.ResultApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiInterface: ApiInterface
) : BaseViewModel() {

    val responseLogin: MutableLiveData<ResultApi<String>> = MutableLiveData()


    fun handleLogin(context: Context, phone: String, pass: String) {
        isLoading.value = true
        UserManager.getInstance().login(
            context,
            phone,
            pass,
            sucess = {
                    isLoading.value = false
                    responseLogin.value = it
            },
            failt = {
                    isLoading.value = false
                    responseLogin.value = it
            })
    }

    fun createGroup(userId: String, phone: String, device: String) {
        isLoading.value = true
        GroupManager.getInstance().getGroupByName(
            phone,
            device,
            sucess = {
                    isLoading.value = false
            },
            failt = {
                    isLoading.value = false
            })
    }

}
