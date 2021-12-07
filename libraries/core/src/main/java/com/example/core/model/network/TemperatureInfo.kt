package com.example.core.model.network

import com.google.gson.annotations.SerializedName

class TemperatureInfo(
    @SerializedName("temperature_type_name") val temperature_type_name: String,
    @SerializedName("temperature_type") val temperature_type: String    //0: Normal(通常) 1:Refrigerate(冷蔵) 2:frozen(冷凍) 3:Special(特殊)
)