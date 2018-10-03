package com.agustin.mlshop.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductSeller(val id: String?,
                         @SerializedName("power_seller_status") val sellerStatus: String?) : Serializable {

    fun hasSellerStatus(): Boolean {
        return sellerStatus != null
    }
}