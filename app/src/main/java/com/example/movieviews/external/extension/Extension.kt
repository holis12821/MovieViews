package com.example.movieviews.external.extension

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieviews.data.models.Genre
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.external.constant.EXTRA_DATAIl_MOVIE
import com.example.movieviews.external.constant.EXTRA_MOVIE
import com.example.movieviews.external.constant.EXTRA_TV_SHOW_MOVIE
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make

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

fun SwipeRefreshLayout.onSetRefreshListener(listener: () -> Unit) {
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

fun ImageView.setImageFromDrawable(drawable: Drawable?) {
    if (drawable == null) return
    Glide.with(this)
        .load(drawable)
        .apply(RequestOptions().override(600, 600))
        .into(this)
}

fun showSnackBar(view: View, message: String) {
    make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun TextView.setSpannable(
    text: List<Genre>?,
    spannableStringBuilder: SpannableStringBuilder = SpannableStringBuilder()
) {
    if (text.isNullOrEmpty()) return

    val joinedText = text.joinToString(", ") { genre ->
        genre.name.toString()
    }
    spannableStringBuilder.append(joinedText)
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

fun showToast(context: Context, message: String) {
    makeText(
        context, message,
        Toast.LENGTH_SHORT
    )
        .show()
}

fun <T> Context.navigateUp(activity: Class<T>) {
    val intent = Intent(this, activity)
    startActivity(intent)
}

fun <T> Context.navigateUpWithData(
    activity: Class<T>,
    key: String?,
    data: MovieResult?,
    flags: Boolean = false
) {
    if (data == null && key.isNullOrEmpty()) return
    val intent = Intent(this, activity).apply {
        if (flags) putExtra(EXTRA_MOVIE, data)
        else putExtra(EXTRA_TV_SHOW_MOVIE, data)
        putExtra(EXTRA_DATAIl_MOVIE, flags)
    }
    startActivity(intent)
}











