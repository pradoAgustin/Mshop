package com.agustin.mlshop.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.agustin.mlshop.R
import kotlinx.android.synthetic.main.info_row.view.*

class infoRowView :LinearLayout {
    constructor(context: Context,title: String, label :String) :this(context) {
        initializeview(title, label)
    }
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, attributeSetId: Int) : super(context, attrs, attributeSetId)

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.info_row, this, true)
        orientation  = VERTICAL
    }

    private fun initializeview(title: String, label: String) {
            infoTitleLabel.text = title
            infoLabel.text = label
    }
}