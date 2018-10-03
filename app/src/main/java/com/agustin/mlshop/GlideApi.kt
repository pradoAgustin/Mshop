package com.agustin.mlshop

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object GlideApi {
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view.context)
                .load(url)
                .apply(RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_background))
                .into(view)
    }
}