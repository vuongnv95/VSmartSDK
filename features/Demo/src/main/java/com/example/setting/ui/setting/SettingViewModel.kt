package com.example.setting.ui.setting

import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
) : BaseViewModel() {

    val dataSetting: MutableLiveData<List<SettingData>> = MutableLiveData()

    fun getDataSetting(){
        val data = mutableListOf<SettingData>()
        data.add(SettingData(1,"Thêm người dùng"))
        dataSetting.value = data
    }
}
