package com.example.movieviews.external.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("app:setImage")
fun ImageView.setImage(urlPath: String?) {
    if (urlPath.isNullOrEmpty()) return
    Glide.with(this)
        .load(urlPath)
        .apply(RequestOptions().override(600, 600))
        .into(this)
}

