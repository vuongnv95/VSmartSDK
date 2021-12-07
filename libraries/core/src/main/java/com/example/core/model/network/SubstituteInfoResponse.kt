package com.example.core.model.network

import com.google.gson.annotations.SerializedName

class SubstituteInfoResponse<D : BaseResponse>(
    @SerializedName("item_name") var item_name: String,
    @SerializedName("phone_number") var phoneNumber: String,
    @SerializedName("substitute_item_list") var dataResponse: D?
) : BaseResponse()