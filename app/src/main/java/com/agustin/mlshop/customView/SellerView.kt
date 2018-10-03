package com.agustin.mlshop.customView

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.agustin.mlshop.R
import com.agustin.mlshop.domain.Address
import com.agustin.mlshop.domain.ProductSeller
import kotlinx.android.synthetic.main.seller_view.view.*

class SellerView : ConstraintLayout {

    lateinit var seller: ProductSeller
    lateinit var address: Address

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, attributeSetId: Int) : super(context, attrs, attributeSetId)

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.seller_view, this, true)
    }

    fun initializeView(seller: ProductSeller?, sellerAddress: Address?) {
        sellerAddress?.also {
            if (it.hasCityAndState()) {
                addInfoRow(it.cityName!!, it.stateName!!)
            } else {
                Log.e("MISSING_FIELD", "missing city name adn state from sellerAddress")
            }

        }
        seller?.also {
            if (it.hasSellerStatus()) {
                addInfoRow(resources.getString(R.string.sellerStatusLabel), it.sellerStatus!!)
            } else {
                Log.e("MISSING_FIELD", "missing seller status")
            }
        }
        if (sellersInfoContainer.childCount > 0) {
            sellersLabel.setOnClickListener {
                sellersInfoContainer.visibility = if (sellersInfoContainer.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
        }
    }

    private fun addInfoRow(title: String, label: String) {
        val infoRow = infoRowView(context, title, label)
        sellersInfoContainer.addView(infoRow)
    }

    private fun initializeSeller(seller: ProductSeller) {
        this.seller
    }
}