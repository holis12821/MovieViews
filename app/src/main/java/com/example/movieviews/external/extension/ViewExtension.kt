package com.example.movieviews.external.extension

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

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











