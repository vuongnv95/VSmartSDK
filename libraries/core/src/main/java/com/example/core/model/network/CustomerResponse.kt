package com.example.core.model.network

import com.google.gson.annotations.SerializedName

class CustomerResponse(
    @SerializedName("order_no") val order_no: String,
    @SerializedName("receipt_type_name") val receipt_type_name: String,
    @SerializedName("total_item_quantity") val total_item_quantity: Int,
    @SerializedName("customer_name") val customer_name: String,
    @SerializedName("container_count") var container_count: Int,
    @SerializedName("cooler_count") var cooler_count: Int,
    @SerializedName("case_count") var case_count: Int,
    @SerializedName("phone_number") val phone_number: String,
    @SerializedName("stock_out_type") val stock_out_type: Int,  //0,1,2 =>0:Cần liên lạc khi thay thế,  1:Không cần liên lạc khi thay thế, 2: Cancel nếu thiếu
    @SerializedName("stock_out_type_name") val stock_out_type_name: String,
    @SerializedName("stock_out_badge_type") val stock_out_badge_type: String,
    @SerializedName("message") val message: String,
    @SerializedName("agree_substitute") var agree_substitute: Boolean?,
    @SerializedName("agree_substitute_check") var agree_substitute_check: Int,  //0: unconfirmed(未確認), 1:confirmed(確認済み)
    @SerializedName("updatable") val is_updatable: Boolean

)