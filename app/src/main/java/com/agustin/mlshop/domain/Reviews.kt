package com.agustin.mlshop.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Reviews(@SerializedName("rating_average") val ratingAverage: Double?,
                   val total: Int?) : Serializable