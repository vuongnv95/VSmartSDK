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
        SDKConfig.startWithAppId(SDKConfigData(this, "http://125.212.248.229:4437", "5d706a8b-43bd-4922-bdde-5524d31c25e7", "", ""))
        SDKConfig.debugMode = true
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}