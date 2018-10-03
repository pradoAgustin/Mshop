package com.agustin.mlshop

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class MLShopRecylerViewHolder<T>(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
    lateinit var view: View
    override fun onClick(v: View?) {
        // do nothing
    }

    fun initialize(v: View) {
        view = v
        view.setOnClickListener(this)
    }

    abstract fun bindItem(item: T)

}