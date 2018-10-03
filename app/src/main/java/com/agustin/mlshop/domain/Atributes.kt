package com.agustin.mlshop.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Atributes(@SerializedName("value_id") val valueId: String?,
                     @SerializedName("value_name") val valueName: String?,
                     val id: String?,
                     val name: String?) : Serializable