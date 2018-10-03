package com.agustin.mlshop.Service

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.agustin.mlshop.domain.MainActivityListProductsResponse
import com.agustin.mlshop.domain.ProductsByCategoryResponse
import com.agustin.mlshop.utils.LogError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MeliShopRepository {

    private val meliShopApiRequest: MeliShopApiRequest = MeliShopRetrofitRequest.retrofitInstance.create(MeliShopApiRequest::class.java)

    fun getMainScreenProducts(): MutableLiveData<MainActivityListProductsResponse> {
        val mutableLiveDataResponse: MutableLiveData<MainActivityListProductsResponse> = MutableLiveData()
        meliShopApiRequest.getMainActivityProducts().enqueue(object : Callback<MainActivityListProductsResponse> {
            override fun onFailure(call: Call<MainActivityListProductsResponse>?, t: Throwable?) {
                Log.d("error", t?.printStackTrace().toString())
                LogError(t?.printStackTrace().toString())
            }

            override fun onResponse(call: Call<MainActivityListProductsResponse>?, response: Response<MainActivityListProductsResponse>?) {
                if (response?.isSuccessful == true) {
                    mutableLiveDataResponse.value = response.body()
                }
            }

        })
        return mutableLiveDataResponse
    }

    fun getProductsByCategory(categoryId: String, offset: Int): MutableLiveData<ProductsByCategoryResponse> {
        return getProductsByCategoryWithOffset(categoryId, offset)
    }

    fun getProductsByCategoryWithOffset(categoryId: String, offset: Int): MutableLiveData<ProductsByCategoryResponse> {
        val mutableLiveDataResponse: MutableLiveData<ProductsByCategoryResponse> = MutableLiveData()
        meliShopApiRequest.getProductsByCategory(categoryId, offset.toString()).enqueue(object : Callback<ProductsByCategoryResponse> {
            override fun onFailure(call: Call<ProductsByCategoryResponse>?, t: Throwable?) {
                LogError(t?.printStackTrace().toString())
            }

            override fun onResponse(call: Call<ProductsByCategoryResponse>?, response: Response<ProductsByCategoryResponse>?) {
                if (response?.isSuccessful == true) {
                    mutableLiveDataResponse.value = response.body()
                }
            }

        })

        return mutableLiveDataResponse
    }

}