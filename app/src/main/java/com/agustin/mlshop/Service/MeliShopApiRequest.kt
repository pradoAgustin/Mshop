package com.agustin.mlshop.Service


import com.agustin.mlshop.domain.MainActivityListProductsResponse
import com.agustin.mlshop.domain.ProductsByCategoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MeliShopApiRequest {

    @GET(".")
    fun getMainActivityProducts(): Call<MainActivityListProductsResponse>

    @GET("search/")
    fun getProductsByCategory(@Query("category") categoryId: String,
                              @Query("offset") offset: String? = "0",
                              @Query("official_store_id") storeId: String? = "all",
                              @Query("limit") limit: String? = "15"): Call<ProductsByCategoryResponse>

}