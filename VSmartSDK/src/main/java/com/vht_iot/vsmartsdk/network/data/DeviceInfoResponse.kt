package com.vht_iot.vsmartsdk.network.data

import com.google.gson.annotations.SerializedName

data class DeviceInfoResponse(
    @SerializedName("deviceid")
    var deviceId: String = "",
    @SerializedName("apikey")
    var apiKey: String? = null,
    @SerializedName("chipid")
    var chipId: String? = null,
    @SerializedName("accept")
    var accept: String? = null,
    @SerializedName("devicename")
    var deviceName: String = "",
    @SerializedName("ipdevice")
    var ipdevice: String = "",
    @SerializedName("type")
    var type: String = "",
    @SerializedName("code")
    var code: Int = -1,
    @SerializedName("response")
    var response: String? = null,
    @SerializedName("gatewayid")
    var gatewayId: String = "",
    @SerializedName("name")
    var productName: String = "",
    @SerializedName("endpoint")
    var endPoint: String = "",
    @SerializedName("vendor")
    var vendor: String = ""
)
