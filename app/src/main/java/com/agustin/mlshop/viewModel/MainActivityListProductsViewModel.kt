package com.agustin.mlshop.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.agustin.mlshop.Service.MeliShopRepository
import com.agustin.mlshop.domain.MainActivityListProductsResponse

class MainActivityListProductsViewModel : ViewModel() {

    fun getMainActivityProducts(): LiveData<MainActivityListProductsResponse> {
        return MeliShopRepository.getMainScreenProducts()
    }
}