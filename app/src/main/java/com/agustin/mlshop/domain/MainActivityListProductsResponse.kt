package com.agustin.mlshop.domain

import com.google.gson.annotations.SerializedName

data class MainActivityListProductsResponse(
        val id: String,
        val name: String,
        @SerializedName("country_id") val countryId: String?,
        @SerializedName("default_currency_id") val defaultCurrencyId: String?,
        val categories: List<ProductCategory>?,
        val currencies: List<ProductCurrency>?
) {
    fun filterCategoriesByName(): Map<String, String>? {
        return categories?.map { it.name to it.id }?.toMap()
    }
}