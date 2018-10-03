package com.agustin.mlshop.domain

import com.google.gson.annotations.SerializedName

data class PagingInfo(val total: Int? = 0,
                      val offset: Int? = 0,
                      val limit: Int? = 0,
                      @SerializedName("primary_results") val primaryResult: Int? = 0)