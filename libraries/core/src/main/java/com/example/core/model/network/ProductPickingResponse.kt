package com.example.core.model.network

import com.google.gson.annotations.SerializedName

class ProductPickingResponse(
    @SerializedName("image_path") val image_path: String,
    @SerializedName("category_name") val category_name: String,
    @SerializedName("category_code") val category_code: String,
    @SerializedName("department") val department: String,
    @SerializedName("temperature_badge_type") val temperature_badge_type: String,
    @SerializedName("temperature_type_name") val temperature_type_name: String,
    @SerializedName("temperature_type") val temperature_type: String,   //0: Normal(通常) 1:Refrigerate(冷蔵) 2:frozen(冷凍) 3:Special(特殊)
    @SerializedName("item_code") val item_code: String,
    @SerializedName("item_name") val item_name: String,
    @SerializedName("item_unit_price_fmt") private var item_unit_price_fmt: String, //Ví dụ là "￥1,234"
    @SerializedName("item_unit_price") var item_unit_price: Double, //Ví dụ là "￥1,234"
    var edtPrice: String = "",

    @SerializedName("order_no") val order_no: String,
    @SerializedName("jan_code") val jan_code: JanCode,
    @SerializedName("sub_jan_codes") val sub_jan_codes: ArrayList<JanCode>,
    @SerializedName("total_purchase_amount") val total_purchase_amount: Int,
    @SerializedName("total_picked_amount") val total_picked_amount: Int,
    @SerializedName("purchase_amounts") val purchase_amounts: ArrayList<Int>,
    @SerializedName("picked_amounts") var picked_amounts: ArrayList<Int>,
    @SerializedName("substitute_items") val substitute_item: ArrayList<SubstituteProduct>,
    @SerializedName("nonplu_flg") val nonplu_flg: Boolean = false,
    @SerializedName("stockout_flg_list") val stockout_flg_list: ArrayList<Int> // 1 : clicked, 0: else
) {

    fun getItemUnitPriceFmt() = item_unit_price_fmt.replace(",", "").replace(".", "")
    fun setItemUnitPriceFmt(price: String) {
        item_unit_price_fmt = price
    }

    fun isProductPicked(): Boolean {
        substitute_item.forEach {
            if (it.has_substitute) {
                return true
            }
        }

        picked_amounts.forEach {
            if (it > 0) {
                return true
            }
        }

        stockout_flg_list.forEach {
            if (it == 1) {
                return true
            }
        }

        return false
    }

    fun isProductPicked(positionOrder: Int): Boolean {
        if (substitute_item[positionOrder].has_substitute) {
            return true
        }

        if (picked_amounts[positionOrder] > 0) {
            return true
        }

        if (stockout_flg_list[positionOrder] == 1) {
            return true
        }

        return false
    }

    fun increaseNumberPicking(positionOrder: Int): Boolean {
        if (picked_amounts[positionOrder] < purchase_amounts[positionOrder]) {
            picked_amounts[positionOrder] += 1
            return true
        }
        return false
    }

    fun decreaseNumberPicking(positionOrder: Int): Boolean {
        if (picked_amounts[positionOrder] > 0) {
            picked_amounts[positionOrder] -= 1
            return true
        }
        return false
    }

    fun isPickingDone(positionOrder: Int): Boolean {
        if (substitute_item[positionOrder].has_substitute) {
            return true
        }

        if (picked_amounts[positionOrder] == purchase_amounts[positionOrder]) {
            return true
        }

        if (stockout_flg_list[positionOrder] == 1) {
            return true
        }

        if (purchase_amounts[positionOrder] == 0) {
            return true
        }

        return false
    }


    fun clearProductPicked(positionOrder: Int) {
//        substitute_item[positionOrder].has_substitute = false

        picked_amounts[positionOrder] = 0

        stockout_flg_list[positionOrder] = 0

    }

    fun getTotalPicked(): Int {
        var totalPicked = 0
        picked_amounts.forEach {
            totalPicked += it
        }

        substitute_item.forEach {
            if (it.has_substitute) {
                totalPicked += it.quantity
            }
        }
        return totalPicked
    }

    fun isFullPicking(positionOrder: Int) =
        picked_amounts[positionOrder] == purchase_amounts[positionOrder]

    fun savePickedProduct() {

    }
}