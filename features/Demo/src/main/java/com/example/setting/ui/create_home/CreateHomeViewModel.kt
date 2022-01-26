package com.example.setting.ui.create_home

import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import com.vht_iot.vsmartsdk.future.organization.OrganizationManager
import com.vht_iot.vsmartsdk.network.data.ResultApi
import com.vht_iot.vsmartsdk.network.data.VOrganizationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateHomeViewModel @Inject constructor(
) : BaseViewModel() {

    val responseOrganization: MutableLiveData<ResultApi<VOrganizationResponse>> = MutableLiveData()
    val responseEror: MutableLiveData<ResultApi<String>> = MutableLiveData()

    fun createHome(homeName: String, address: String) {
        isLoading.value = true
        OrganizationManager.getInstance().createOrganizations(
            name = homeName,
            lat = 20.908358,
            lon = 105.7887,
            address = address,
            success = {
                isLoading.value = false
                responseOrganization.value = it
            },
            failt = {
                isLoading.value = false
                responseEror.value = it
            })
    }

}
