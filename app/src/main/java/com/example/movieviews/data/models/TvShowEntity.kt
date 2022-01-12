package com.example.movieviews.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowEntity(
    val id: Int,
    val posterUrl: String,
    val title: String,
    val tagLine: String,
    val releaseDate: String,
    val rating: Double,
    val overview: String,
    val genres: List<String>,
    val episode: Int
): Parcelable
