package com.example.baseproject

import android.app.Application
import com.example.core.BuildConfig
import com.example.setting.model.Ahihi
import com.vht_iot.vsmartsdk.sdk_config.SDKConfig
import com.vht_iot.vsmartsdk.sdk_config.SDKConfigData
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication @Inject constructor() : Application() {

    @Inject
    lateinit var ahihi: Ahihi

    override fun onCreate() {
        super.onCreate()
        SDKConfig.startWithAppId(SDKConfigData(this, "http://116.101.122.190:4437", "", ""))
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}