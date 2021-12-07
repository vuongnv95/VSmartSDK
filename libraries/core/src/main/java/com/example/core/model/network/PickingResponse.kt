package com.example.core.model.network

import com.google.gson.annotations.SerializedName

class PickingResponse(
    @SerializedName("customer_list") var listCustomer: ArrayList<CustomerResponse>,
    @SerializedName("item_list") var listProductPicking: List<ProductPickingResponse>,
    @SerializedName("category_list") val listCategory: List<CategoryResponse>,
    @SerializedName("temperature_list") val temperatureList: List<TemperatureInfo>,
    @SerializedName("appointed_date") val appointed_date: String,
    @SerializedName("timerange_code") val timerange_code: String,
    @SerializedName("map_image_path") val map_image_path: String,
    @SerializedName("sort") val sort: String,
    @SerializedName("updatable") val updatable: Boolean,
    @SerializedName("error_messages") val error_messages: List<String>
) : BaseResponse()
