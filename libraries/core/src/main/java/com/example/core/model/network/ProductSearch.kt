package com.example.core.model.network

import com.google.gson.annotations.SerializedName

data class ProductSearch(
    @SerializedName("request_scan_code") val request_scan_code: String,
    @SerializedName("nonplu_flg") val nonplu_flg: Boolean,
    @SerializedName("item_code") val item_code: String,
    @SerializedName("price") val price: Int,
    @SerializedName("error_message") val error_message: String
) : BaseResponse()