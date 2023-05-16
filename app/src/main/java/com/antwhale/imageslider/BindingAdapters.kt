package com.antwhale.imageslider

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageURL")
fun setImageURL(imageView: ImageView, url: String?) {
    try {
        Glide.with(imageView.context).load(url).into(imageView)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}