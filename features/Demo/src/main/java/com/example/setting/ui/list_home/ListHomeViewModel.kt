package com.example.setting.ui.list_home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.vht_iot.vsmartsdk.future.organization.OrganizationManager
import com.vht_iot.vsmartsdk.network.data.ResultApi
import com.vht_iot.vsmartsdk.network.data.VOrganizationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListHomeViewModel @Inject constructor(
) : BaseViewModel() {

    val listHomeResponse: MutableLiveData<List<VOrganizationResponse>> = MutableLiveData()
    val errorResponse: MutableLiveData<ResultApi<String>> = MutableLiveData()

    fun loadDataDevice() {

        OrganizationManager.getInstance().getOrganizations(sucess = {
            viewModelScope.launch {
                listHomeResponse.value = it.data?.dataResponse?.filter { it.org_id.isEmpty() }
            }
        }, failt = {
            errorResponse.value = it
        })

    }
}
