package com.example.core.model.network

import com.google.gson.annotations.SerializedName

class LoginResponse : BaseResponse() {

    @SerializedName("timestamp")
    val timestamp: String = ""

    @SerializedName("status")
    val status: Int = 0

    @SerializedName("error")
    val error: String = ""

    @SerializedName("message")
    val message: String = ""
}