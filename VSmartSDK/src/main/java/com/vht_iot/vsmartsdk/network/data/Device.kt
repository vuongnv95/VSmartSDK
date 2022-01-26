package com.vht_iot.vsmartsdk.network.data

import com.google.gson.annotations.SerializedName

data class Device(
    val id: String = "",
    val name: String = "",
    @SerializedName("org_id")
    val organizationID: String = "",
    @SerializedName("org_name")
    val organizationName: String = "",
    @SerializedName("group_id")
    val groupID: String = "",
    @SerializedName("group_name")
    val groupName: String = "",
    val status: String = "",
    val template_id: String = "",
    @SerializedName("type_id")
    val typeID: String = "",
    @SerializedName("type_name")
    val typeName: String = "",
    @SerializedName("attributes")
    val attributes: List<VAttributeResponse>? = listOf()
)