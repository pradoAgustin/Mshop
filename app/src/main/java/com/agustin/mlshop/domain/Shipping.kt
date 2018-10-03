package com.agustin.mlshop.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Shipping(@SerializedName("free_shipping") val freeShipping: Boolean? = false,
                    @SerializedName("store_pick_up") val storePickUp: Boolean? = false) : Serializable {
}