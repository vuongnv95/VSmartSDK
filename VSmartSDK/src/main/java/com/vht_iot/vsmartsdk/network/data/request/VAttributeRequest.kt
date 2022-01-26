package com.vht_iot.vsmartsdk.network.data.request

import com.google.gson.annotations.SerializedName
import com.vht_iot.vsmartsdk.utils.VDefine

data class VAttributeRequest(
    @SerializedName("attribute_type")
    val attributeType: String = VDefine.ScopeType.SCOPE_SERVER,
    @SerializedName("attribute_key")
    val attributeKey: String = "",
    @SerializedName("new_attribute_key")
    val newAttributeKey: String = "",
    @SerializedName("logged")
    val logged: Boolean = false,
    @SerializedName("value")
    val value: String = "",
    @SerializedName("value_t")
    val valueType: String = ""
)