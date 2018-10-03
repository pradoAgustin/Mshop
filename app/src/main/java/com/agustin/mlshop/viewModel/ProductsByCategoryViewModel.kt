package com.agustin.mlshop.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.agustin.mlshop.Service.MeliShopRepository
import com.agustin.mlshop.domain.ProductsByCategoryResponse

class ProductsByCategoryViewModel : ViewModel() {

    fun getProductsByCategory(categoryId: String, offset: Int = 0): LiveData<ProductsByCategoryResponse> {
        return MeliShopRepository.getProductsByCategory(categoryId, offset)
    }
}