package com.agustin.mlshop

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.agustin.mlshop.domain.MainActivityListProductsResponse
import com.agustin.mlshop.domain.ProductCategory
import com.agustin.mlshop.utils.LogError
import com.agustin.mlshop.viewModel.MainActivityListProductsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_products_row.view.*


class MainActivity : BaseActivity() {

    private val mainActivityListProductsViewModel: MainActivityListProductsViewModel by lazy {
        ViewModelProviders.of(this).get(MainActivityListProductsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityListProductsViewModel
        mainActivityListProductsViewModel.getMainActivityProducts().observe(this, Observer { mainActivityProductsResponse ->
            initializeUI(mainActivityProductsResponse)
        })
    }

    private fun initializeUI(mainActivityProductsResponse: MainActivityListProductsResponse?) {
        initializeCategories(mainActivityProductsResponse?.categories)
        initializeAutoCompleteTextView(mainActivityProductsResponse?.filterCategoriesByName())
    }

    private fun initializeAutoCompleteTextView(categories: Map<String, String>?) {
        val map = categories
        val adapter = ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, categories?.keys?.toMutableList())
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val categoryId = map?.get(parent.getItemAtPosition(position).toString())
            if (categoryId != null) {
                ProductsByCategoryActivity.start(this@MainActivity, categoryId)
            } else {
                LogError("missing category id for autocomplete textview in main Activity")
            }
        }

    }

    private fun initializeCategories(categories: List<ProductCategory>?) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MainActivityListProductsRecyclerAdapter(categories)
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }


    inner class MainActivityListProductsRecyclerAdapter(list: List<ProductCategory?>?) : MLshopRecyclerAdapter<ProductCategory?, MainProductsRecyclerViewHolder>(list) {

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainProductsRecyclerViewHolder {
            val inflatedView = LayoutInflater.from(p0.context).inflate(R.layout.main_products_row, p0, false)

            return MainProductsRecyclerViewHolder(inflatedView)
        }

    }


    inner class MainProductsRecyclerViewHolder(inflatedView: View) : MLShopRecylerViewHolder<ProductCategory?>(inflatedView) {
        private var id: String? = null

        init {
            initialize(inflatedView)
        }

        override fun bindItem(item: ProductCategory?) {
            view.mainProductRowTitle.text = item?.name
            id = item?.id
            if (id == null || view.mainProductRowTitle.text == null) {
                LogError("categories in main activity have null params")
                view.visibility = View.GONE
            } else {
                view.visibility = View.VISIBLE
            }
        }

        override fun onClick(v: View?) {
            ProductsByCategoryActivity.start(this@MainActivity, id!!)
        }
    }

}
