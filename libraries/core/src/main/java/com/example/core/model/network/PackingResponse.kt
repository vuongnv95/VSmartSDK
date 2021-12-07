package com.example.core.model.network

import com.google.gson.annotations.SerializedName

class PackingResponse(
    @SerializedName("customer_list") var listCustomer: ArrayList<CustomerResponse>,
    @SerializedName("item_list") var listProductPacking: List<ProductPackingResponse>,
    @SerializedName("appointed_date") val appointed_date: String,
    @SerializedName("timerange_code") val timerange_code: String,
    @SerializedName("sort") val sort: String,
    @SerializedName("customer_index") val customer_index: Int,
    @SerializedName("map_image_path") val map_image_path: String,
    @SerializedName("error_messages") val error_messages: List<String>,
    @SerializedName("updatable") val updatable: Boolean
) : BaseResponse()
