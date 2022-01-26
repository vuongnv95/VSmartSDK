package com.vht_iot.vsmartsdk.network.data

import com.google.gson.annotations.SerializedName

data class VAttributeResponse(
    @SerializedName("attribute_type")
    val attributeType: String = "",
    @SerializedName("attribute_key")
    val attributeKey: String = "",
    @SerializedName("logged")
    val logged: Boolean = false,
    @SerializedName("value")
    val value: Any = Any(),
    @SerializedName("value_as_string")
    val valueAsString: String = "",
    @SerializedName("last_update_ts")
    val lastUpdateTs: Long = 0,
    @SerializedName("value_type")
    val valueType: String = "",
)