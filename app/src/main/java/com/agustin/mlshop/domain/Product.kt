package com.agustin.mlshop.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal
import java.math.RoundingMode

data class Product(val id: String?,
                   val title : String?,
                   val seller : ProductSeller?,
                   val price : BigDecimal?= BigDecimal.ZERO,
                   @SerializedName("currency_id") val currencyId :String?,
                   @SerializedName("available_quantity") val availableQuantity :Int?=0,
                   @SerializedName("sold_quantity")val soldQuantity :Int?=0,
                   @SerializedName("listing_type_id") val listingTypeId :String?,
                   @SerializedName("stop_time") val stopTime :String?,
                   val condition : String?,
                   val thumbnail :String?,
                   @SerializedName("accepts_mercadopago") val acceptsMercadoPago : Boolean?=false,
                   val installments : Installments?,
                   val address : Address?,
                   val shipping : Shipping?,
                   val atributes : Atributes?,
                   @SerializedName("original_price") val originalPrice : BigDecimal?= BigDecimal.ZERO,
                   @SerializedName("category_id") val categoryId :String?,
                   @SerializedName("official_store_id") val officialStoreId : Int?,
                   val reviews : Reviews?
                   ) :Serializable {
    fun hasFreeShipping(): Boolean {
        return shipping?.freeShipping == true
    }

    fun isLastItem(): Boolean {
       return 1 == availableQuantity
    }

    fun getItemDiscount(): BigDecimal {
        var discountPercentage = BigDecimal.ZERO
        if(originalPrice != null && price != null) {
            discountPercentage = (originalPrice - price) * BigDecimal(100) / originalPrice
        }
        return discountPercentage.setScale(1, RoundingMode.HALF_UP)
    }
}