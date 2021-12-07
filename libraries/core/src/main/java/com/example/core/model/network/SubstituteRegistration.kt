package com.example.core.model.network

import com.google.gson.annotations.SerializedName

class SubstituteRegistration(
    @SerializedName("item_code") var item_code: String,
    @SerializedName("image_path") var image_path: String,
    @SerializedName("item_name") var item_name: String,
    @SerializedName("jan_code") var jan_code: String,
    @SerializedName("quantity") var quantity: Int,
    @SerializedName("price") var price: Int,
    @SerializedName("success_messages") var success_messages: List<String>,
    @SerializedName("error_messages") var error_messages: List<String>
) : BaseResponse()