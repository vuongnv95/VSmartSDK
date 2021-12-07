package com.vht_iot.vsmartsdk.utils

import com.vht_iot.vsmartsdk.network.connect.NetworkException
import com.vht_iot.vsmartsdk.network.data.ErrorCode
import com.vht_iot.vsmartsdk.network.data.ResultApi

class HandleError {
    companion object {
        fun handCommonError(e: Exception, failt: (ResultApi<String>) -> Unit) {
            if (e is NetworkException) {
                failt(ResultApi.VSmartError(ErrorCode.CODE_NETWORK, "NetworkException"))
            } else {
                failt(ResultApi.VSmartError(ErrorCode.ERROR_SERVER, e.localizedMessage.toString()))
            }
        }
    }
}