package com.vht_iot.vsmartsdk.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject


class VDefine {
    companion object {
        //param
        const val PARAM_PASSWORD = "password"
        const val PARAM_IDENTIFIER = "identifier"
        var useAddminToken = false
        var useAppKeySecret = false
        const val PREF_FILE_NAME = "SDK_IOT.Preferences"
    }

    class ConfigSDK {
        companion object {
            var BASE_URL = "http://125.212.248.229:4437"
            var APP_KEY = "nlaDOC8uS6Xn7L0JIcPD"
            var APP_SECRET = "yKeMoImiHp9DUXxoGpERza31xSyCWunW"
        }
    }

    class EndPointBE{
        companion object{
            const val LOGIN = "/api/login"
        }
    }

    class ConfigNetwork{
        companion object{
            const val DEFAULT_TIMEOUT = 30
            const val READ_TIMEOUT = 10
        }
    }

    class ParamApi{
        companion object{
            const val PARAM_IDENTIFIER = "identifier"
            const val PARAM_PASSWORD = "password"
            const val PARAM_EMAIL = "email"
            const val PARAM_PHONE = "phone"
            const val PARAM_PROJECT_ID = "project_id"
            const val PARAM_OTP = "otp"
            const val PARAM_NAME = "name"
            const val PARAM_ENTITY_TYPE = "entity_type"
            const val PARAM_GROUP_ID = "group_id"
            const val PARAM_ORG_ID = "org_id"
            const val PARAM_USER_ID = "user_id"
            const val PARAM_DESCRIPTION = "description"
            const val PARAM_OTP_TYPE = "otp_type"
        }
    }

    class OTPType{
        companion object{
            const val REGISTER = "register"
            const val RESET_PASSWORD = "forgot"
        }
    }
    class EntityType{
        companion object{
            const val ORGANIZATION = "ORGANIZATION"
            const val DEVICE = "DEVICE"
            const val EVENT = "EVENT"
            const val USER = "USER"
        }
    }
}

fun createBodyMap(data: MutableMap<String, String>): RequestBody {
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