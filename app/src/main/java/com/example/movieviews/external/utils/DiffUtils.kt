package com.example.movieviews.external.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.models.Review

object DiffUtils {
    val COMPARATOR_MOVIES = object : DiffUtil.ItemCallback<MovieResult>() {
        override fun areItemsTheSame(
            oldItem: MovieResult, newItem: MovieResult
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MovieResult, newItem: MovieResult
        ): Boolean = oldItem == newItem
    }

    val COMPARATOR_REVIEW = object : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(
            oldItem: Review, newItem: Review
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Review, newItem: Review
        ): Boolean = oldItem == newItem
    }

}