package com.example.core.model.network

import com.google.gson.annotations.SerializedName

class CategoryResponse(
    @SerializedName("company_code") val company_code: String,
    @SerializedName("category_code") val category_code: String,
    @SerializedName("category_name") val category_name: String,
    @SerializedName("parent_category_code") val parent_category_code: String,
    @SerializedName("depth") val depth: String,
    @SerializedName("display_order") val display_order: String
)