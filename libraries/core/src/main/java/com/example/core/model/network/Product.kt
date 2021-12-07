package com.example.core.model.network

open class Product(
    open val image_path: String,
    open val category_name: String,
    open val category_code: String,
    open val department: String,
    open val temperature_badge_type: String,
    open val temperature_type_name: String,
    open val temperature_type: String,   //0: Normal(通常) 1:Refrigerate(冷蔵) 2:frozen(冷凍) 3:Special(特殊)
    open val item_code: String,
    open val item_name: String,
    open val item_unit_price_fmt: String, //Ví dụ là "￥1,234"
    open val jan_code: JanCode,
    open val sub_jan_codes: ArrayList<JanCode>,
    open val nonplu_flg: Boolean = false
)