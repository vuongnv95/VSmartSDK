package com.vht_iot.vsmartsdk.utils


class VDefine {
    companion object {
        //param
        const val PARAM_PASSWORD = "password"
        const val PARAM_IDENTIFIER = "identifier"
        var useAddminToken = false
        const val PREF_FILE_NAME = "SDK_IOT.Preferences"
    }

    class  ConfigSDK{
        companion object{
            var BASE_URL = "http://116.101.122.190:4437"
            var APP_ID = "BASE_URL"
            var APP_SCERET_KEY = "BASE_URL"
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
            const val PARAM_USER_ID = "user_id"
        }
    }

    class OTPType{
        companion object{
            const val REGISTER = 0
            const val RESET_PASSWORD = 1
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