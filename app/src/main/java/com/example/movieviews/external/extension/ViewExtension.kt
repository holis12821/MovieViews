package com.example.movieviews.external.extension

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieviews.data.models.Genre

/**
 * This ViewExtension contains general extension and you can call
 * extension function to specified view
 * */

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun Int.dpToPixel(context: Context): Int {
    return (this.times(context.resources.displayMetrics.density)).toInt()
}

fun convertDpToPixel(context: Context, dp: Int): Int {
    return (dp.times(context.resources.displayMetrics.density)).toInt()
}

fun convertPixelsToDp(context: Context, px: Int): Int {
    return (px.div(context.resources.displayMetrics.density)).toInt()
}

fun SwipeRefreshLayout.swipeVisible() {
    isRefreshing = true
}

fun SwipeRefreshLayout.swipeGone() {
    isRefreshing = false
}

fun SwipeRefreshLayout.setOnRefreshListener(listener: () -> Unit) {
    this.setOnRefreshListener {
        listener.invoke()
    }
}

fun RecyclerView.setupHorizontalLayoutManager() {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}

fun RecyclerView.setupVerticalLayoutManager() {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
}

fun RecyclerView.setupGridLayoutManager(column: Int) {
    layoutManager = GridLayoutManager(context, column)
}

fun TextView.setSpannable(
    text: List<Genre>?,
    spannableStringBuilder: SpannableStringBuilder = SpannableStringBuilder()
) {
    if (text.isNullOrEmpty()) return
    val addOnTextList = mutableListOf<Genre>()
    addOnTextList.addAll(text)
    var addOnText = ""
    addOnTextList.forEachIndexed { index, textItem ->
        addOnText += textItem.name
        if (index < addOnTextList.size - 1) {
            addOnText += ", "
        }
    }
    spannableStringBuilder.append(addOnText)
    setText(spannableStringBuilder)
}

fun TextView.setSpan(
    textFirst: String?,
    textSecond: String?,
    spannableStringBuilder: SpannableStringBuilder = SpannableStringBuilder()
) {
    if (textFirst.isNullOrEmpty() && textSecond.isNullOrEmpty()) return
    spannableStringBuilder.append("$textFirst * $textSecond")
    text = spannableStringBuilder
}


fun ImageView.setImage(urlPath: String?) {
    if (urlPath.isNullOrEmpty()) return
    Glide.with(this)
        .load(urlPath)
        .apply(RequestOptions().override(600, 600))
        .into(this)
}











