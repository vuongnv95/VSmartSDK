package com.example.core.model.network

import com.google.gson.annotations.SerializedName

class ProductPackingResponse(
    @SerializedName("image_path") val image_path: String,
    @SerializedName("category_name") val category_name: String,
    @SerializedName("category_code") val category_code: String,
    @SerializedName("department") val department: String,
    @SerializedName("temperature_badge_type") val temperature_badge_type: String,
    @SerializedName("temperature_type_name") val temperature_type_name: String,
    @SerializedName("temperature_type") val temperature_type: String,   //0: Normal(通常) 1:Refrigerate(冷蔵) 2:frozen(冷凍) 3:Special(特殊)
    @SerializedName("item_code") val item_code: String,
    @SerializedName("item_name") val item_name: String,
    @SerializedName("item_unit_price_fmt") val item_unit_price_fmt: String, //Ví dụ là "￥1,234"
    @SerializedName("order_no") val order_no: String,
    @SerializedName("jan_code") val jan_code: JanCode,
    @SerializedName("sub_jan_codes") val sub_jan_codes: ArrayList<JanCode>,
    @SerializedName("total_purchase_amount") val total_purchase_amount: Int,
    @SerializedName("total_packed_amount") var total_packed_amount: Int,
    @SerializedName("total_picked_record") val total_picked_record: Int,
    @SerializedName("purchase_amounts") val purchase_amounts: ArrayList<Int>,
    @SerializedName("packed_amounts") val packed_amounts: ArrayList<Int>,
    @SerializedName("picked_records") val picked_records: ArrayList<Int>,
    @SerializedName("nonplu") val nonplu: Boolean = false,
    @SerializedName("substitute") val substitute: Boolean = false,
    @SerializedName("substitute_origin") val substitute_origin: Boolean = false,
    @SerializedName("stock_item_code") val stock_item_code: String,
) {

    fun getItemUnitPriceFmt() = item_unit_price_fmt.replace(",", "").replace(".", "")

    fun isProductPacked(): Boolean {

        packed_amounts.forEach {
            if (it > 0) {
                return true
            }
        }

        return false
    }

    fun isProductPacked(positionOrder: Int): Boolean {

        if (packed_amounts[positionOrder] > 0) {
            return true
        }
        return false
    }

    //    fun increaseNumberPicking(positionOrder: Int): Boolean {
//        if (picked_amounts[positionOrder] < purchase_amounts[positionOrder]) {
//            picked_amounts[positionOrder] += 1
//            return true
//        }
//        return false
//    }
//
//    fun decreaseNumberPicking(positionOrder: Int): Boolean {
//        if (picked_amounts[positionOrder] > 0) {
//            picked_amounts[positionOrder] -= 1
//            return true
//        }
//        return false
//    }
//
    fun isPackingDoneScan(positionOrder: Int): Boolean {
        if (!nonplu && !substitute) {
            if (packed_amounts[positionOrder] == purchase_amounts[positionOrder] && picked_records[positionOrder] < purchase_amounts[positionOrder]) {
                return true
            }
        } else {
            if (packed_amounts[positionOrder] == picked_records[positionOrder]) {
                return true
            }
        }
        if (purchase_amounts[positionOrder] == 0) {
            return true
        }

        return false
    }

    fun isPackingDone(positionOrder: Int): Boolean {
        if (!nonplu && !substitute) {
            if (packed_amounts[positionOrder] == purchase_amounts[positionOrder]) {
                return true
            }
        } else {
            if (packed_amounts[positionOrder] == picked_records[positionOrder]) {
                return true
            }
        }
        if (purchase_amounts[positionOrder] == 0) {
            return true
        }

        return false
    }

    fun getTotalPacked(): Int {
        var totalPacked = 0
        packed_amounts.forEach {
            totalPacked += it
        }

        return totalPacked
    }
//
//    fun clearProductPicked(positionOrder: Int) {
//        substitute_item[positionOrder].has_substitute = false
//
//        picked_amounts[positionOrder] = 0
//
//        stockout_flg_list[positionOrder] = 0
//
//        item_unit_price = 0
//    }
//
//    fun getTotalPicked(): Int {
//        var totalPicked = 0
//        picked_amounts.forEach {
//            totalPicked += it
//        }
//
//        substitute_item.forEach {
//            if (it.has_substitute) {
//                totalPicked += it.quantity
//            }
//        }
//        return totalPicked
//    }
//
//    fun isFullPicking(positionOrder: Int) =
//        picked_amounts[positionOrder] == purchase_amounts[positionOrder]

    fun savePickedProduct() {

    }

    //packing
    fun clearProductPacked(positionOrder: Int) {
        packed_amounts[positionOrder] = 0
    }

    fun isEnoughPacked(positionOrder: Int): Boolean {
        return packed_amounts[positionOrder] < picked_records[positionOrder]
    }
}