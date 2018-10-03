package com.agustin.mlshop.domain

import com.google.gson.annotations.SerializedName

data class ProductsByCategoryResponse(val id: String,
                                      val name: String,
                                      val paging : PagingInfo?,
                                      @SerializedName("default_currency_id") val defaultCurrencyId : String,
                                      val categories : List<ProductCategory>?,
                                      val currencies : List<ProductCurrency>?,
                                      val results :List<Product>?)