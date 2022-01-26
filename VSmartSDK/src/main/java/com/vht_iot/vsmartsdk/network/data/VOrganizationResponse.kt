package com.vht_iot.vsmartsdk.network.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VOrganizationResponse(
    val id: String = "",
    val name: String = "",
    @SerializedName("org_id")
    var orgId: String = "",
    @SerializedName("sub_orgs")
    val subOrg: List<VOrganizationResponse>? = listOf(),
    @SerializedName("attributes")
    var attributes: List<VAttributeResponse>? = listOf(),
    val project_id: String = "",
    val description: String = "",
    val group_id: String = "",
) : Serializable