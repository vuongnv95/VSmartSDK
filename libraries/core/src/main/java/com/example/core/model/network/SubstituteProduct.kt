package com.example.core.model.network

import com.google.gson.annotations.SerializedName

class SubstituteProduct(
    @SerializedName("has_substitute") var has_substitute: Boolean,
    @SerializedName("image_path") var image_path: String,
    @SerializedName("jan_code") var jan_code: JanCode,
    @SerializedName("item_name") var item_name: String,
    @SerializedName("item_unit_price") var unit_price: Double,
    @SerializedName("quantity") var quantity: Int

)
