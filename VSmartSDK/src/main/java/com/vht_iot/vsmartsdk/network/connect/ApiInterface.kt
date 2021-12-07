package com.vht_iot.vsmartsdk.network.connect

import com.vht_iot.vsmartsdk.network.data.DeviceResponse
import com.vht_iot.vsmartsdk.network.data.response.LoginResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST("/api/login")
    suspend fun login(@Body requestBody: RequestBody): LoginResponse

    @DELETE("/v2/user/logout")
    suspend fun logout(): ApiObjectResponse<Any>

    @POST("/api/users/otp/register")
    suspend fun sendVerificationCode(phone: String, type: String)

    @GET("/api/devices")
    suspend fun getListDevice(): DeviceResponse
}