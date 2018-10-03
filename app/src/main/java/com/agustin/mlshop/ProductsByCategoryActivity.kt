package com.agustin.mlshop

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.agustin.mlshop.domain.Product
import com.agustin.mlshop.domain.ProductsByCategoryResponse
import com.agustin.mlshop.viewModel.ProductsByCategoryViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.products_by_category_row.view.*
import java.util.*


class ProductsByCategoryActivity : BaseActivity() {
    private lateinit var productsByCategoryViewModel: ProductsByCategoryViewModel
    private lateinit var productsByCategoryAdapter: ProductsByCategoryAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var primaryResult: Int? = null
    private var productSortExtra: Boolean? = null
    private val lastVisibleItemPosition: Int
        get() = linearLayoutManager.findLastVisibleItemPosition()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productSortExtra = savedInstanceState?.getSerializable(PRODUCT_SORT_EXTRA) as Boolean?
        val categoryId = intent.getStringExtra(CATEGORY_ID_EXTRA)
        productsByCategoryViewModel = ViewModelProviders.of(this).get(ProductsByCategoryViewModel::class.java)
        productsByCategoryViewModel.getProductsByCategory(categoryId).observe(this, Observer { productsByCategoryResponse ->
            initializeUI(productsByCategoryResponse)
        })
    }

    private fun initializeUI(productsByCategoryResponse: ProductsByCategoryResponse?) {
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        productsByCategoryAdapter = ProductsByCategoryAdapter(productsByCategoryResponse?.results, productSortExtra)
        recyclerView.adapter = productsByCategoryAdapter
        updatePrimaryResult(productsByCategoryResponse?.paging?.primaryResult)
        setRecyclerViewScrollListener()
    }

    private fun setRecyclerViewScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView!!.layoutManager.itemCount
                val lastVisibleItemPosition = lastVisibleItemPosition + 1
                if (totalItemCount == lastVisibleItemPosition && (primaryResult?.compareTo(lastVisibleItemPosition)
                                ?: lastVisibleItemPosition) > 0) {
                    requestMoreProducts()
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putSerializable(PRODUCT_SORT_EXTRA, productsByCategoryAdapter.productSortByHighestPrice)
        super.onSaveInstanceState(outState)
    }

    private fun requestMoreProducts() {
        productsByCategoryViewModel.getProductsByCategory(intent.getStringExtra(CATEGORY_ID_EXTRA), lastVisibleItemPosition).observe(this, Observer { productsByCategoryResponse ->
            updateProductsRecyclerView(productsByCategoryResponse)
        })
    }

    private fun updateProductsRecyclerView(productsByCategoryResponse: ProductsByCategoryResponse?) {
        productsByCategoryAdapter.updateItems(productsByCategoryResponse?.results)
        updatePrimaryResult(productsByCategoryResponse?.paging?.primaryResult)
    }

    private fun updatePrimaryResult(size: Int?) {
        primaryResult = size
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.category_products_activity
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.category_sort_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.highestPrice -> productsByCategoryAdapter.sortByHighestPrice()

            R.id.lowestPrice -> productsByCategoryAdapter.sortByLowestPrice()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val CATEGORY_ID_EXTRA = "CATEGORY_ID_EXTRA"
        const val PRODUCT_SORT_EXTRA = "PRODUCT_SORT_EXTRA"

        fun start(context: Context, id: String) {
            val intent = Intent(context, ProductsByCategoryActivity::class.java)
            intent.putExtra(CATEGORY_ID_EXTRA, id)
            context.startActivity(intent)
        }
    }

    inner class ProductsByCategoryAdapter(results: List<Product>?) : MLshopRecyclerAdapter<Product, ProductsByCategoryRecyclerViewHolder>(results) {
        var productSortByHighestPrice: Boolean? = null

        constructor(results: List<Product>?, sortByHighestPrice: Boolean?) : this(results){
            productSortByHighestPrice = sortByHighestPrice
            if(productSortByHighestPrice == true) {
                sortByHighestPrice()
            } else if(productSortByHighestPrice == false) {
                sortByLowestPrice()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsByCategoryRecyclerViewHolder {
            val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.products_by_category_row, parent, false)
            return ProductsByCategoryRecyclerViewHolder(inflatedView)
        }

        fun sortByLowestPrice() {
            adapterList = sortByAscending(adapterList)
            productSortByHighestPrice = false
            notifyDataSetChanged()
        }

        private fun sortByAscending(list: List<Product>): List<Product> =
                list.sortedBy { product -> product.price }

        fun sortByHighestPrice() {
            adapterList = sortByDescending(adapterList)
            productSortByHighestPrice = true
            notifyDataSetChanged()
        }

        private fun sortByDescending(list: List<Product>): List<Product> =
                list.sortedByDescending { product -> product.price }


        fun updateItems(list: List<Product>?) {
            onItemsInserted(sortIfRequired(list))
        }

        private fun sortIfRequired(results: List<Product>?): List<Product>? {
            var sortedResult = results ?: Collections.emptyList()
            productSortByHighestPrice?.also {
                sortedResult = if (it) sortByDescending(sortedResult) else sortByAscending(sortedResult)
            }
            return sortedResult
        }

    }

    inner class ProductsByCategoryRecyclerViewHolder(inflatedView: View) : MLShopRecylerViewHolder<Product>(inflatedView) {

        init {
            initialize(inflatedView)
        }

        override fun bindItem(item: Product) {
            view.productByCategoryTitle.text = item.title
            view.productByCategoryPrice.text = item.price.toString()
            view.productbyCategoryCurrency.text = item.currencyId
            val lastItemVisibility = if (item.isLastItem()) View.VISIBLE else View.GONE
            view.hotItem.visibility = lastItemVisibility

            item.thumbnail?.also {
                GlideApi.loadImage(view.productThumbnail, item.thumbnail)
            }

        }

        override fun onClick(v: View?) {
            ProductDetailActivity.start(this@ProductsByCategoryActivity, productsByCategoryAdapter.getItem(this.adapterPosition))
        }

    }

}

