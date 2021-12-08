package com.example.setting.ui.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.core.network.ApiInterface
import com.vht_iot.vsmartsdk.future.user_manager.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiInterface: ApiInterface
) : BaseViewModel() {

    val statusLogin: MutableLiveData<Boolean> = MutableLiveData()

    fun handleLogin(context: Context, phone: String, pass: String) {
        isLoading.value = true
        UserManager.getInstance().login(
            context,
            phone,
            pass,
            sucess = {
                viewModelScope.launch {
                    isLoading.value = false
                    statusLogin.value = true
                }
            },
            failt = {
                viewModelScope.launch {
                    isLoading.value = false
                    statusLogin.value = false
                }
            })
    }

}
