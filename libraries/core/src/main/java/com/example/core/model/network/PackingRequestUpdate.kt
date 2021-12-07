package com.example.core.model.network

import com.google.gson.annotations.SerializedName

data class PackingRequestUpdate(
    @SerializedName("packing_list") var packing_list: PackingResponse
)