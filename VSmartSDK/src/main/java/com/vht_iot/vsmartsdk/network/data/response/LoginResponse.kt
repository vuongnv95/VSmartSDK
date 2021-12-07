package com.vht_iot.vsmartsdk.network.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val user: UserResponse,
    val at: String,
    val rt: String,
    val region: String,
    val token: String,
    @SerializedName("devicetoken")
    val deviceToken: String,
    @SerializedName("ezviztoken")
    val ezvizToken: String,
    @SerializedName("area_domain")
    val areaDomain: String
)