package com.example.core.model.network

import com.google.gson.annotations.SerializedName

class OrderResponse(
    @SerializedName("order_no") val orderNo: String,
    @SerializedName("account_name") val accountName: String,
    @SerializedName("address_name") val addressName: String,
    @SerializedName("appointed_date") val appointedDate: String?,
    @SerializedName("timerange_code") val timerangeCode: String,
    @SerializedName("timerange_name") val timerangeName: String,
    @SerializedName("delivery_status") val deliveryStatus: Int,
    @SerializedName("receipt_type") val receiptType: Int,
    @SerializedName("total_item_quantity") val totalItemQuantity: Int,
    @SerializedName("special_item_quantity") val specialItenQuantity: Int,
    @SerializedName("total_item_price") val totalItemPrice: Int,
    @SerializedName("store_code") val storeCode: String,
    @SerializedName("store_name") val storeName: String,
    @SerializedName("message") val message: String?,
    @SerializedName("picking_status") val pickingStatus: Int,
    @SerializedName("packing_status") val packingStatus: Int
) : BaseResponse()