package com.vht_iot.vsmartsdk.utils


class Define {
    companion object {
        //param
        const val PARAM_PASSWORD = "password"
        const val PARAM_IDENTIFIER = "identifier"
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
        }
    }
}