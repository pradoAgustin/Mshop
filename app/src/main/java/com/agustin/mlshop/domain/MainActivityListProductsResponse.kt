package com.agustin.mlshop.domain

import com.agustin.mlshop.utils.LogError
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
        if (categories != null) {
            val map = HashMap<String, String>()
            for (category in categories) {
                if (category.name != null && category.id != null) {
                    map[category.name] = category.id
                } else {
                    LogError("category from service with bad parameters")
                }

            }
            return map
        }
        return null
    }
}