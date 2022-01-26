package com.vht_iot.vsmartsdk.network.data.request

import com.google.gson.annotations.SerializedName

data class DataAddRequest(
    val name: String = "",
    @SerializedName("org_id")
    val orgId: String = "",
    val attributes: List<VAttributeRequest>? = listOf()
)