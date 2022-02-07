package com.vht_iot.vsmartsdk.future.device

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.vht_iot.vsmartsdk.network.connect.ApiInterface
import com.vht_iot.vsmartsdk.network.data.Device
import com.vht_iot.vsmartsdk.network.data.DeviceInfoResponse
import com.vht_iot.vsmartsdk.network.data.ResultApi
import com.vht_iot.vsmartsdk.network.data.request.DataAddRequest
import com.vht_iot.vsmartsdk.network.data.request.VAttributeRequest
import com.vht_iot.vsmartsdk.sdk_config.SDKConfig
import com.vht_iot.vsmartsdk.utils.HandleError
import com.vht_iot.vsmartsdk.utils.VDefine
import com.viettel.vht.core.pref.AppPreferences
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject

class ConfigDeviceEzManager {

    private var addDeviceRetryInterval: Long = 5000
    private var addDeviceRetryCount: Int = 0

    private var job: Job = Job()
    internal var apiInterface: ApiInterface? = null

    init {
        apiInterface = SDKConfig.apiInterface
    }

    val scope = CoroutineScope(Dispatchers.IO + job)
    val mainScope = CoroutineScope(Dispatchers.Main + job)

    companion object {
        @Volatile
        private var instance: ConfigDeviceEzManager? = null
        private var TAG = "ConfigDeviceEzManager"
        fun getInstance(): ConfigDeviceEzManager =
            instance ?: synchronized(this) {
                instance ?: ConfigDeviceEzManager()
            }
    }

    fun getDeviceInfo(
        context: Context,
        host: String,
        success: (ResultApi<String>) -> Unit,
        failt: (ResultApi<String>) -> Unit
    ) {
        val url = "http://$host/"
        scope.launch {
            delay(addDeviceRetryInterval)
            try {
                val response = apiInterface?.sendRequestToDevice("""${url}device""")
                response?.let {
                    it.ipdevice = host
                    addDeviceInfo(context, it, url, success, failt)
                }
            } catch (e: Exception) {
                if (addDeviceRetryCount > 9) {
                    HandleError.handCommonError(e, failt)
                    if (SDKConfig.debugMode) {
                        Log.d(TAG, "createDevices() called err :$e")
                    }
                } else {
                    addDeviceRetryCount++
                    addDeviceRetryInterval += 2000
                    getDeviceInfo(context, host, success, failt)
                }
            }
        }
    }

    private fun addDeviceInfo(
        context: Context, deviceInfoInfo: DeviceInfoResponse?, url: String,
        success: (ResultApi<String>) -> Unit,
        failt: (ResultApi<String>) -> Unit
    ) {
        scope.launch {
            try {
                var deviceRequest = DataAddRequest()
                val listAttributes: MutableList<VAttributeRequest> = arrayListOf()
                deviceInfoInfo?.let {
                    if (it.type.isNotEmpty()) {
                        listAttributes.add(
                            VAttributeRequest(
                                attributeKey = VDefine.AttributeKey.TYPE,
                                value = it.type,
                                valueType = VDefine.ValueType.STR
                            )
                        )
                    }
                    if (it.gatewayId.isNotEmpty()) {
                        listAttributes.add(
                            VAttributeRequest(
                                attributeKey = VDefine.AttributeKey.GATEWAY_ID,
                                value = it.gatewayId,
                                valueType = VDefine.ValueType.STR
                            )
                        )
                    }
                    if (it.deviceId.isNotEmpty()) {
                        listAttributes.add(
                            VAttributeRequest(
                                attributeKey = VDefine.AttributeKey.DEVICE_ID,
                                value = it.deviceId,
                                valueType = VDefine.ValueType.STR
                            )
                        )
                    }
                    if (it.productName.isNotEmpty()) {
                        listAttributes.add(
                            VAttributeRequest(
                                attributeKey = VDefine.AttributeKey.PRODUCT_NAME,
                                value = it.productName,
                                valueType = VDefine.ValueType.STR
                            )
                        )
                    }
                    if (it.endPoint.isNotEmpty()) {
                        listAttributes.add(
                            VAttributeRequest(
                                attributeKey = VDefine.AttributeKey.END_POINT,
                                value = it.endPoint,
                                valueType = VDefine.ValueType.STR
                            )
                        )
                    }
                    if (it.ipdevice.isNotEmpty()) {
                        listAttributes.add(
                            VAttributeRequest(
                                attributeKey = VDefine.AttributeKey.IP,
                                value = it.ipdevice,
                                valueType = VDefine.ValueType.STR
                            )
                        )
                    }
                    val orgId = AppPreferences.getInstance(context).getCurrentHome()
                    deviceRequest =
                        orgId?.let { it1 ->
                            DataAddRequest(
                                name = it.deviceName,
                                orgId = it1,
                                attributes = listAttributes
                            )
                        }!!
                }

                val dataRequest = Gson().toJson(deviceRequest)

                val body: RequestBody =
                    RequestBody.create(
                        "application/json".toMediaTypeOrNull(),
                        dataRequest.toString()
                    )
                val device = apiInterface?.addDevice(body)
                device?.let {
                    sendDataToDispatchServer(context, url, it, success, failt)
                }
            } catch (e: Exception) {
                HandleError.handCommonError(e, failt)
            }
        }
    }

    private fun sendDataToDispatchServer(
        context: Context, url: String, deviceResponse: Device,
        success: (ResultApi<String>) -> Unit,
        failt: (ResultApi<String>) -> Unit
    ) {
        scope.launch {
            try {
                val jsonObject = JSONObject()
                jsonObject.put("deviceid", deviceResponse.id)
                if (!AppPreferences.getInstance(context).getDeviceToken().isNullOrEmpty()) {
                    jsonObject.put(
                        VDefine.ParamApi.PARAM_DEVICE_TOKEN,
                        AppPreferences.getInstance(context).getDeviceToken()
                    )
                }

                val body: RequestBody =
                    RequestBody.create(
                        "application/json".toMediaTypeOrNull(),
                        jsonObject.toString()
                    )

                val response = apiInterface?.sendDataToDispatchServer("""${url}ap""", body)
                response?.let {
                    mainScope.launch {
                        success(
                            ResultApi.VSmartSuccess("")
                        )
                    }
                }
            } catch (e: Exception) {
                HandleError.handCommonError(e, failt)
                if (SDKConfig.debugMode) {
                    Log.d(TAG, "sendDataToDispatchServer() called err :$e")
                }
            }
        }
    }
}