package com.example.movieviews.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("app:setImage")
fun ImageView.setImage(urlPath: String?) {
    if (urlPath.isNullOrEmpty()) return
    Glide.with(this)
        .load(urlPath)
        .apply(RequestOptions().override(600, 600))
        .into(this)
}

