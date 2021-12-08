package com.vht_iot.vsmartsdk.future.user_manager

import android.content.Context
import android.util.Log
import com.vht_iot.vsmartsdk.network.connect.ApiInterface
import com.vht_iot.vsmartsdk.network.data.ErrorCode
import com.vht_iot.vsmartsdk.network.data.ResultApi
import com.vht_iot.vsmartsdk.sdk_config.SDKConfig
import com.vht_iot.vsmartsdk.utils.Define
import com.vht_iot.vsmartsdk.utils.HandleError
import com.viettel.vht.core.pref.AppPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject

/**
 * Quản lý thông tin người dùng.
 */
class UserManager() {
    private var job: Job? = null
    internal var apiInterface: ApiInterface? = null

    init {
        apiInterface = SDKConfig.apiInterface
    }

    companion object {
        @Volatile
        private var instance: UserManager? = null
        private var TAG = "UserManager"
        fun getInstance(): UserManager =
            instance ?: synchronized(this) {
                instance ?: UserManager()
            }
    }

    /**
     * logout account user.
     */
    fun logout(
        sucess: (ResultApi<String>) -> Unit,
        failt: (ResultApi<String>) -> Unit
    ) {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                apiInterface?.logout()
                sucess(ResultApi.VSmartSuccess(""))
                if (SDKConfig.debugMode) {
                    Log.d(TAG, "logout() called success")
                }
            } catch (e: Exception) {
                HandleError.handCommonError(e, failt)
                if (SDKConfig.debugMode) {
                    Log.d(TAG, "logout() called error : ${e}")
                }
            }
        }
    }

    /**
     * login with sub user.
     */
    fun login(
        context: Context,
        phone: String,
        pass: String,
        sucess: (ResultApi<String>) -> Unit,
        failt: (ResultApi<String>) -> Unit
    ) {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = mutableMapOf<String, String>()
                data.put(Define.ParamApi.PARAM_IDENTIFIER, phone)
                data.put(Define.ParamApi.PARAM_PASSWORD, pass)
                val body = createBodyMap(data)
                val loginResponse = apiInterface?.login(body)
                loginResponse?.let {
                    AppPreferences.getInstance(context).setUserToken(it.token, it.deviceToken)
                    if (SDKConfig.debugMode) {
                        Log.d(TAG, "login() called success : ${it}")
                    }
                }
                sucess(ResultApi.VSmartSuccess(""))
            } catch (e: Exception) {
                HandleError.handCommonError(e, failt)
                if (SDKConfig.debugMode) {
                    Log.d(TAG, "login() called err :$e")
                }
            }
        }
    }

    private fun createBodyMap(data: MutableMap<String, String>): RequestBody {
        val jsonObject = JSONObject()
        for ((key, value) in data) {
            jsonObject.put(key, value)
        }
        val body: RequestBody =
            RequestBody.create(
                "application/json".toMediaTypeOrNull(), jsonObject.toString()
            )
        return body
    }

    /**
     * when receive code from phone, handle verify to server.
     */
    fun sendVerificationCode(
        phone: String,
        type: String,
        sucess: (ResultApi<String>) -> Unit,
        failt: (ResultApi<String>) -> Unit
    ) {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                apiInterface?.sendVerificationCode(phone, type)
                sucess(ResultApi.VSmartSuccess(""))
                if (SDKConfig.debugMode) {
                    Log.d(TAG, "sendVerificationCode() called success")
                }
            } catch (e: Exception) {
                if (SDKConfig.debugMode) {
                    Log.d(TAG, "sendVerificationCode() called err :$e")
                }
                HandleError.handCommonError(e, failt)
            }
        }
    }

    fun onDestroy() {
        job?.cancel()
    }

    /**
     * handle register sub user with phone
     */
    fun registerUserWithPhone(
        phone: String,
        pass: String,
        projectId: String,
        sucess: (ResultApi<String>) -> Unit,
        failt: (ResultApi<String>) -> Unit
    ) {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = mutableMapOf<String, String>()
                data.put(Define.ParamApi.PARAM_PHONE, phone)
                data.put(Define.ParamApi.PARAM_PROJECT_ID, projectId)
                val body = createBodyMap(data)
                val registerResponse = apiInterface?.registerUserWithPhone(body)
                if (registerResponse != null) {
                    if (SDKConfig.debugMode) {
                        Log.d(TAG, "registerUser() called success : ${registerResponse}")
                    }
                    setPassUser(phone, projectId, pass, registerResponse.otp, sucess, failt)
                } else {
                    failt(
                        ResultApi.VSmartError(
                            ErrorCode.ERROR_SERVER,
                            "Không thể thực hiện đăng kí"
                        )
                    )
                }

            } catch (e: Exception) {
                HandleError.handCommonError(e, failt)
                if (SDKConfig.debugMode) {
                    Log.d(TAG, "registerUser() called err :$e")
                }
            }
        }
    }

    private fun setPassUser(
        phone: String,
        projectId: String,
        pass: String,
        otp: String,
        sucess: (ResultApi<String>) -> Unit,
        failt: (ResultApi<String>) -> Unit
    ) {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = mutableMapOf<String, String>()
                data.put(Define.ParamApi.PARAM_PHONE, phone)
                data.put(Define.ParamApi.PARAM_PROJECT_ID, projectId)
                data.put(Define.ParamApi.PARAM_PASSWORD, pass)
                data.put(Define.ParamApi.PARAM_OTP, otp)
                val body = createBodyMap(data)
                val passwordResponse = apiInterface?.registerUserWithPhone(body)
                if (SDKConfig.debugMode) {
                    Log.d(TAG, "setPassUser() called success")
                }
                sucess(
                    ResultApi.VSmartSuccess("")
                )
            } catch (e: Exception) {
                HandleError.handCommonError(e, failt)
                if (SDKConfig.debugMode) {
                    Log.d(TAG, "setPassUser() called err :$e")
                }
            }
        }
    }


    /**
     * handle register sub user with phone
     */
    fun registerUserWithEmail(
        email: String,
        pass: String,
        projectId: String,
        sucess: (ResultApi<String>) -> Unit,
        failt: (ResultApi<String>) -> Unit
    ) {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = mutableMapOf<String, String>()
                data.put(Define.ParamApi.PARAM_EMAIL, email)
                data.put(Define.ParamApi.PARAM_PROJECT_ID, projectId)
                data.put(Define.ParamApi.PARAM_PASSWORD, pass)
                val body = createBodyMap(data)
                val registerResponse = apiInterface?.registerUserWithEmail(body)
                if (registerResponse != null) {
                    if (SDKConfig.debugMode) {
                        Log.d(TAG, "registerUser() called success : ${registerResponse}")
                    }
                    sucess(
                        ResultApi.VSmartSuccess(
                            registerResponse.identity_id
                        )
                    )
                } else {
                    failt(
                        ResultApi.VSmartError(
                            ErrorCode.ERROR_SERVER,
                            "Không thể thực hiện đăng kí"
                        )
                    )
                }

            } catch (e: Exception) {
                HandleError.handCommonError(e, failt)
                if (SDKConfig.debugMode) {
                    Log.d(TAG, "registerUser() called err :$e")
                }
            }
        }
    }
}