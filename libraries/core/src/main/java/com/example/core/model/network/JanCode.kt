package com.example.core.model.network

import com.google.gson.annotations.SerializedName

class JanCode(
    @SerializedName("first") var first: String, //Phần JAN CODE trừ 4 kí tự cuối (JANコードの下4桁以外)
    @SerializedName("last") var last: String    //4 kí tự cuối của JAN CODE (JANコードの下4桁)
) {
    fun getJanCodeOneLine() = first + last
}