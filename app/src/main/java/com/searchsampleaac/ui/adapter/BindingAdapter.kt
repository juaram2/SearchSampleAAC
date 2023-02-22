package com.searchsampleaac.ui.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object BindingAdapter {
    @JvmStatic
    @androidx.databinding.BindingAdapter("image")
    fun loadImage(imageView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            if (it.isNotEmpty()) {
                Glide
                    .with(imageView)
                    .load(it)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imageView)
                    .clearOnDetach()
            }
        }
    }
}