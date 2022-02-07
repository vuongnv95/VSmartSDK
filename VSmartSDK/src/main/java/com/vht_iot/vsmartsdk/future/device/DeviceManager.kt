package com.vht_iot.vsmartsdk.future.device

import android.util.Log
import com.vht_iot.vsmartsdk.future.organization.OrganizationManager
import com.vht_iot.vsmartsdk.network.connect.ApiInterface
import com.vht_iot.vsmartsdk.network.data.Device
import com.vht_iot.vsmartsdk.network.data.DeviceResponse
import com.vht_iot.vsmartsdk.network.data.ResultApi
import com.vht_iot.vsmartsdk.network.data.VOrganizationResponse
import com.vht_iot.vsmartsdk.sdk_config.SDKConfig
import com.vht_iot.vsmartsdk.utils.HandleError
import com.vht_iot.vsmartsdk.utils.VDefine
import com.vht_iot.vsmartsdk.utils.createBodyMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Quản lý thông tin thiết bị.
 */
class DeviceManager() {
    private var job: Job = Job()
    internal var apiInterface: ApiInterface? = null

    init {
        apiInterface = SDKConfig.apiInterface
    }

    val scope = CoroutineScope(Dispatchers.IO + job)
    val mainScope = CoroutineScope(Dispatchers.Main + job)

    companion object {
        @Volatile
        private var instance: DeviceManager? = null
        private var TAG = "DeviceManager"
        fun getInstance(): DeviceManager =
            instance ?: synchronized(this) {
                instance ?: DeviceManager()
            }
    }

    fun addDevice(
        name: String,
        sucess: (ResultApi<Device>) -> Unit,
        failt: (ResultApi<String>) -> Unit
    ) {
        scope.launch {
            try {
                val data = mutableMapOf<String, String>()
                data.put(VDefine.ParamApi.PARAM_NAME, name)
                val deviceResponse = apiInterface?.addDevice(createBodyMap(data))
                deviceResponse?.let {
                    if (SDKConfig.debugMode) {
                        Log.d(TAG, "createDevices() called success : ${deviceResponse}")
                    }
                    mainScope.launch {
                        sucess(
                            ResultApi.VSmartSuccess(
                                it
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                HandleError.handCommonError(e, failt)
                if (SDKConfig.debugMode) {
                    Log.d(TAG, "createDevices() called err :$e")
                }
            }
        }
    }

    /**
     * when receive code from phone, handle verify to server.
     */
    fun getListDevice(
        sucess: (ResultApi<DeviceResponse>) -> Unit,
        failt: (ResultApi<String>) -> Unit
    ) {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = apiInterface?.getListDevice()
                sucess(ResultApi.VSmartSuccess(data))
            } catch (e: Exception) {
                HandleError.handCommonError(e, failt)
            }
        }
    }

    fun onDestroy() {
        job?.cancel()
    }
}