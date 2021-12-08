package com.vht_iot.vsmartsdk.network.connect

import com.vht_iot.vsmartsdk.network.data.DeviceResponse
import com.vht_iot.vsmartsdk.network.data.RegisterResponse
import com.vht_iot.vsmartsdk.network.data.RegisterWithEmailResponse
import com.vht_iot.vsmartsdk.network.data.RegisterWithPhoneResponse
import com.vht_iot.vsmartsdk.network.data.response.LoginResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiInterface {
    @POST("/api/login")
    suspend fun login(@Body requestBody: RequestBody): LoginResponse

    @DELETE("/v2/user/logout")
    suspend fun logout(): ApiObjectResponse<Any>

    @POST("/api/users/otp/register")
    suspend fun sendVerificationCode(phone: String, type: String)

    @GET("/api/devices")
    suspend fun getListDevice(): DeviceResponse

    @POST("/api/users/otp/register")
    suspend fun registerUserWithPhone(@Body requestBody: RequestBody): RegisterWithPhoneResponse

    @PUT("/api/users/otp/newpassword")
    suspend fun setPassword(@Body requestBody: RequestBody): Any

    @POST("/api/users")
    suspend fun registerUserWithEmail(@Body requestBody: RequestBody): RegisterWithEmailResponse
}