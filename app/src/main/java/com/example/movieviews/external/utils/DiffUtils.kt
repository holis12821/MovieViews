package com.example.movieviews.external.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.movieviews.data.models.MovieResult

object DiffUtils {
    val COMPARATOR = object : DiffUtil.ItemCallback<MovieResult>() {
        override fun areItemsTheSame(
            oldItem: MovieResult,
            newItem: MovieResult
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MovieResult,
            newItem: MovieResult
        ): Boolean =
            oldItem == newItem
    }
}