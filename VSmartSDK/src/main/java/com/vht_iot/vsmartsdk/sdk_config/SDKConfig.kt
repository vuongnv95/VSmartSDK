package com.vht_iot.vsmartsdk.sdk_config

import com.vht_iot.vsmartsdk.network.connect.ApiInterface
import com.vht_iot.vsmartsdk.network.connect.module.ApiModule

/**
 * khởi tạo thư viện bằng appid, sceretkey
 */
class SDKConfig {

    companion object {

        /**
         * true: debug mode enable
         * false: debug mode disable
         */
        var debugMode = false
        internal var apiInterface: ApiInterface? = null

        fun startWithAppId(sdkConfig: SDKConfigData) {
            apiInterface =
                ApiModule.getInstance(sdkConfig.application, sdkConfig.url).provideApiInterface()
        }

        @Volatile
        private var instance: SDKConfig? = null

        fun getInstance(): SDKConfig =
            instance ?: synchronized(this) {
                instance ?: SDKConfig()
            }

    }
}