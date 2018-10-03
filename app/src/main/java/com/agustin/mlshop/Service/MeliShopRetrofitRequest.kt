package com.agustin.mlshop.Service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MeliShopRetrofitRequest {

    private val BASE_URL = "https://api.mercadolibre.com/sites/MLU/"
    private val retrofit: Retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val retrofitInstance: Retrofit
        get() {
            return retrofit
        }
}