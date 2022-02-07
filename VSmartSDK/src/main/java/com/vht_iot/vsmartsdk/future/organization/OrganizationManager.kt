package com.vht_iot.vsmartsdk.future.organization

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.vht_iot.vsmartsdk.network.connect.ApiArrayOrgResponse
import com.vht_iot.vsmartsdk.network.connect.ApiInterface
import com.vht_iot.vsmartsdk.network.data.ErrorCode
import com.vht_iot.vsmartsdk.network.data.ResultApi
import com.vht_iot.vsmartsdk.network.data.VOrganizationResponse
import com.vht_iot.vsmartsdk.network.data.request.DataAddRequest
import com.vht_iot.vsmartsdk.network.data.request.VAttributeRequest
import com.vht_iot.vsmartsdk.sdk_config.SDKConfig
import com.vht_iot.vsmartsdk.utils.HandleError
import com.vht_iot.vsmartsdk.utils.VDefine
import com.vht_iot.vsmartsdk.utils.createBodyMap
import com.viettel.vht.core.pref.AppPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OrganizationManager {

    private var job: Job = Job()
    internal var apiInterface: ApiInterface? = null

    init {
        apiInterface = SDKConfig.apiInterface
    }

    val scope = CoroutineScope(Dispatchers.IO + job)
    val mainScope = CoroutineScope(Dispatchers.Main + job)

    companion object {
        @Volatile
        private var instance: OrganizationManager? = null
        private var TAG = "OrganizationManager"
        fun getInstance(): OrganizationManager =
            instance ?: synchronized(this) {
                instance ?: OrganizationManager()
            }
    }

    fun addOrgDefault(
        context: Context,
        success: (ResultApi<String>) -> Unit,
        failt: (ResultApi<String>) -> Unit
    ) {
        scope.launch {
            try {
                val orgResponse = apiInterface?.getOrganizations()
                if (orgResponse != null) {
                    if (SDKConfig.debugMode) {
                        Log.d(
                            TAG,
                            "getListOrganizations() called success : ${orgResponse}"
                        )
                    }
                    if (orgResponse.dataResponse.isEmpty()) {
                        val data = mutableMapOf<String, String>()
                        data.put(VDefine.ParamApi.PARAM_NAME, "My Home")
                        val addOrgResponse = apiInterface?.createOrganizations(createBodyMap(data))
                        addOrgResponse?.let {
                            AppPreferences.getInstance(context).setCurrentHome(it.id)
                            mainScope.launch {
                                success(
                                    ResultApi.VSmartSuccess(
                                        "Add org Success"
                                    )
                                )
                            }
                        }
                    } else {
                        AppPreferences.getInstance(context)
                            .setCurrentHome(orgResponse.dataResponse[0].id)
                        mainScope.launch {
                            success(
                                ResultApi.VSmartSuccess(
                                    ""
                                )
                            )
                        }
                    }
                } else {
                    mainScope.launch {
                        failt(
                            ResultApi.VSmartError(
                                ErrorCode.ERROR_SERVER,
                                "Không thể lấy thông tin Org"
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                HandleError.handCommonError(e, failt)
                if (SDKConfig.debugMode) {
                    Log.d(TAG, "createOrganizations() called err :$e")
                }
            }
        }
    }

    fun createOrganizations(
        name: String,
        lat: Double,
        lon: Double,
        address: String,
        success: (ResultApi<VOrganizationResponse>) -> Unit,
        failt: (ResultApi<String>) -> Unit
    ) {
        scope.launch {
            try {
                val listAttribute: MutableList<VAttributeRequest> = arrayListOf()
                listAttribute.add(
                    VAttributeRequest(
                        attributeKey = VDefine.AttributeKey.LAT,
                        value = lat.toString(),
                        valueType = VDefine.ValueType.DBL
                    )
                )
                listAttribute.add(
                    VAttributeRequest(
                        attributeKey = VDefine.AttributeKey.LONG,
                        value = lon.toString(),
                        valueType = VDefine.ValueType.DBL
                    )
                )
                listAttribute.add(
                    VAttributeRequest(
                        attributeKey = VDefine.AttributeKey.ADDRESS,
                        value = address,
                        valueType = VDefine.ValueType.STR
                    )
                )
                val orgRequest = DataAddRequest(name = name, attributes = listAttribute)
                val data = Gson().toJson(orgRequest)
                val groupResponse =
                    apiInterface?.createOrganizations(createBodyMap(data.toString()))
                groupResponse?.let {
                    if (SDKConfig.debugMode) {
                        Log.d(
                            TAG,
                            "createOrganizations() called success : ${groupResponse}"
                        )
                    }
                    mainScope.launch {
                        success(
                            ResultApi.VSmartSuccess(
                                it
                            )
                        )
                    }
                }

            } catch (e: Exception) {
                HandleError.handCommonError(e, failt)
                if (SDKConfig.debugMode) {
                    Log.d(TAG, "createOrganizations() called err :$e")
                }
            }
        }
    }

    fun getOrganizations(
        sucess: (ResultApi<ApiArrayOrgResponse<VOrganizationResponse>>) -> Unit,
        failt: (ResultApi<String>) -> Unit
    ) {
        scope.launch {
            try {
                val groupResponse = apiInterface?.getOrganizations()
                groupResponse?.let {
                    if (SDKConfig.debugMode) {
                        Log.d(
                            TAG,
                            "createOrganizations() called success : ${groupResponse}"
                        )
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
                    Log.d(TAG, "createOrganizations() called err :$e")
                }
            }
        }
    }

}