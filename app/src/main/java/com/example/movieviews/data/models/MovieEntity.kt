package com.example.movieviews.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    val id: Int,
    val posterUrl: String,
    val title: String,
    val tagLine: String,
    val releaseDate: String,
    val rating: Double,
    val overview: String,
    val genres: List<String>,
    val duration: String,
    val isFreeWatch: Boolean,
    val isTrending: Boolean,
    val isUpComing: Boolean = false,
    val isPopular: Boolean = false
): Parcelable
