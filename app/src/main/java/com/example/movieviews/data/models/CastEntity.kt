package com.example.movieviews.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CastEntity(
   val id: Int = 0,
   val posterUrl: String,
   val originalName: String,
   val nameCast: String
): Parcelable
