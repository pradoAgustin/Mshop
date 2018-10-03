package com.agustin.mlshop.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Address(@SerializedName("state_id") val stateId: String?,
                   @SerializedName("state_name") val stateName: String?,
                   @SerializedName("city_name") val cityName: String?) : Serializable {
    fun hasCityAndState(): Boolean {
        return cityName != null && stateName != null
    }
}