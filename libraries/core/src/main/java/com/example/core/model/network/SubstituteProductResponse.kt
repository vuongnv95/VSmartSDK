package com.example.core.model.network

import com.google.gson.annotations.SerializedName

class SubstituteProductResponse(
    @SerializedName("item_code") val item_code: String,
    @SerializedName("image_path") val image_path: String,
    @SerializedName("producing_area") val producing_area: String?,
    @SerializedName("item_name") val item_name: String?,
    @SerializedName("standard") val standard: String?,
    @SerializedName("jan_code") val jan_code: String,
    @SerializedName("display_jan_code") val display_jan_code: JanCode,   //0: Normal(通常) 1:Refrigerate(冷蔵) 2:frozen(冷凍) 3:Special(特殊)
    @SerializedName("unit_price") val unit_price: Int
) : BaseResponse()