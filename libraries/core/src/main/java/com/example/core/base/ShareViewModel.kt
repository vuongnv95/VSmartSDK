package com.example.core.base

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class ShareViewModel @Inject constructor() : BaseViewModel() {
    val userType = MutableLiveData<String>(null)
}