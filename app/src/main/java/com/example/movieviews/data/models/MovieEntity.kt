package com.example.movieviews.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    //main data
    val id: Int,
    val posterUrl: String,
    val title: String,
    val tagLine: String,
    val releaseDate: String,
    val rating: Double,
    val overview: String,
    val genres: List<String>,
    val cast: List<CastEntity> = listOf(),
    //additional data or attributes
    val duration: String = "",
    val isFreeWatch: Boolean = false,
    val isTrending: Boolean = false,
    val isUpComing: Boolean = false,
    val isPopular: Boolean = false,
    val isTvSHow: Boolean = false,
    val certification: String = "PG-13",
    val episode: Int = 0
): Parcelable
