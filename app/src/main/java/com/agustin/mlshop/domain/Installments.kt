package com.agustin.mlshop.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal

data class Installments(val quantity: Int?,
                        val ammount: BigDecimal?,
                        val rate: Int?,
                        @SerializedName("currency_id") val currencyId: String?) : Serializable