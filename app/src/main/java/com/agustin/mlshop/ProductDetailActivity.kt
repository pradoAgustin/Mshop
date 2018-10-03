package com.agustin.mlshop

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.agustin.mlshop.domain.Product
import com.agustin.mlshop.utils.showDialog
import kotlinx.android.synthetic.main.product_detail.*
import kotlinx.android.synthetic.main.shipping_dialog.view.*
import java.math.BigDecimal

class ProductDetailActivity : BaseActivity() {
    private val product: Product by lazy {
        intent.getSerializableExtra(PRODUCT_EXTRA) as Product
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        initializeMainProductInfo()
        initializeProductPricesAndDiscount()
        initializeReviews()
        intializeShippingDialog()

        sellerView.initializeView(product.seller, product.address)


    }

    override fun getLayoutResourceId(): Int {
        return R.layout.product_detail
    }

    private fun initializeMainProductInfo() {
        product.thumbnail?.also {
            GlideApi.loadImage(detailThumbnail, it)
        }

        productTitle.text = product.title
        productCurrency.text = product.currencyId
        productPrice.text = product.price.toString()
        installmentsLabel.text = String.format(resources.getString(R.string.installlmentLabel), product.installments?.quantity.toString())

        product.availableQuantity?.also {
            initializeSpinner(it)
        }
    }

    private fun intializeShippingDialog() {
        freeShipping.visibility = View.VISIBLE
        freeShipping.setOnClickListener {
            openShippingDetailDialog(product.shipping?.freeShipping)

        }
    }

    private fun initializeReviews() {
        product.reviews?.also {
            productRating.text = String.format(resources.getString(R.string.reviewsQtyLabel), it.total.toString())
            productRating.visibility = View.VISIBLE
        }

    }

    private fun initializeProductPricesAndDiscount() {
        val discountPercentage = product.getItemDiscount()
        product.originalPrice?.also {
            originalPriceCurrency.text = product.currencyId
            originalPriceCurrency.visibility = View.VISIBLE
            originalPriceCurrency.paintFlags = (Paint.STRIKE_THRU_TEXT_FLAG)
            originalPriceWithoutdiscount.text = it.toPlainString()
            originalPriceWithoutdiscount.visibility = View.VISIBLE
        }

        if (discountPercentage > BigDecimal.ZERO) {
            productDiscount.text = String.format(resources.getString(R.string.discountLabel), discountPercentage)
            productDiscount.visibility = View.VISIBLE
        }
    }

    private fun openShippingDetailDialog(shipping: Boolean?) {

        showDialog {
            if (shipping == true) {
                dialogView.pickUpStore.text = resources.getString(R.string.pickUpInStore)
            } else {
                dialogView.pickUpStore.text = resources.getString(R.string.PickUpInStoreUnavailable)
            }
        }.show()
    }

    private fun initializeSpinner(quantity: Int) {

        val qtyList: ArrayList<String> = ArrayList()
        var index = 1
        while (index <= quantity) {
            qtyList.add(index.toString())
            index++
        }

        spinnerQty.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, qtyList)

        //item selected listener for spinner
        spinnerQty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // do nothing
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //do nothing
            }

        }
    }

    companion object {
        const val PRODUCT_EXTRA = "PRODUCT_EXTRA"
        fun start(context: Context, product: Product) {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(PRODUCT_EXTRA, product)
            context.startActivity(intent)
        }
    }
}