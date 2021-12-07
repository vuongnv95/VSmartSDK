package com.vht_iot.vsmartsdk.network.connect

import com.google.gson.annotations.SerializedName

data class ApiObjectResponse<T>(
        @SerializedName("error") var error: Int,
        @SerializedName("msg") var msg: String,
        @SerializedName("data") var dataResponse: T,
)

data class ApiException(
        @SerializedName("error") var error: String,
        @SerializedName("msg") var msg: String,
        @SerializedName("data") var dataResponse: Any
)