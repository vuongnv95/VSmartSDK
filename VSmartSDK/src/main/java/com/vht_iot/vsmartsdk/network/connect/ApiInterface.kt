package com.vht_iot.vsmartsdk.network.connect

import com.vht_iot.vsmartsdk.network.data.*
import com.vht_iot.vsmartsdk.network.data.response.LoginResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiInterface {
    @POST("/api/app/login")
    suspend fun login(@Body requestBody: RequestBody): LoginResponse

    @DELETE("/v2/user/logout")
    suspend fun logout(): ApiObjectResponse<Any>

    @POST("/api/app/otp")
    suspend fun sendVerificationCode(@Body requestBody: RequestBody): VOTPPhoneResponse

    @POST("/api/app/vhome/register")
    suspend fun registerUserWithPhone(@Body requestBody: RequestBody): Any

    @PUT("/api/app/otp/newpassword")
    suspend fun forgotPassword(@Body requestBody: RequestBody): Object

    @POST("/api/users")
    suspend fun registerUserWithEmail(@Body requestBody: RequestBody): RegisterWithEmailResponse

    // group manager

    @GET("/api/groups/{groupName}/{entityType}")
    suspend fun getGroupByName(
        @Path("groupName") groupName: String,
        @Path("entityType") entityType: String
    ): VGroupResponse

    @POST("/api/groups")
    suspend fun createGroup(@Body requestBody: RequestBody): VGroupResponse

    @POST("/api/roles/customer")
    suspend fun createRole(@Body requestBody: RequestBody): Any

    // organizations manager

    @POST("/api/organizations")
    suspend fun createOrganizations(@Body requestBody: RequestBody): VOrganizationResponse

    @GET("/api/organizations/expand")
    suspend fun getOrganizations(
    ): ApiArrayOrgResponse<VOrganizationResponse>

    // device manager
    @GET("/api/devices")
    suspend fun addDevice(@Body requestBody: RequestBody): Device

    @GET("/api/devices")
    suspend fun getListDevice(): DeviceResponse

    //config ap device
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json;charset=UTF-8",
        "Package-Name: com.coolkit",
        "Cache-Control: no-store"
    )
    @GET
    suspend fun sendRequestToDevice(@Url url: String): DeviceInfoResponse

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json;charset=UTF-8",
        "Package-Name: com.coolkit",
        "Cache-Control: no-store"
    )
    @POST
    suspend fun sendDataToDispatchServer(
        @Url url: String,
        @Body requestBody: RequestBody
    ): ApiObjectResponse<Object>

    @POST
    @FormUrlEncoded
    suspend fun sendRequestCompatible(
        @Url url: String,
        @Field("s1") ssid: String,
        @Field("p1") password: String,
        @Field("save") save: String = ""
    ): DeviceInfoResponse

    @GET
    suspend fun restartDevice(@Url url: String): Object

}