package com.example.core.model.network

import com.google.gson.annotations.SerializedName

data class PickingRequestUpdate(
    @SerializedName("picking_list") var picking_list: PickingResponse
)