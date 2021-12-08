package com.vht_iot.vsmartsdk.sdk_config

import com.vht_iot.vsmartsdk.future.user_manager.UserManager
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
        var sdkConfigData: SDKConfigData? = null
        internal var apiInterface: ApiInterface? = null

        fun startWithAppId(sdkConfig: SDKConfigData) {
            sdkConfigData = sdkConfig
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

    fun onDestroy(){
        UserManager.getInstance().onDestroy()
    }
}