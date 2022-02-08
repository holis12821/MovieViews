package com.example.movieviews.data.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class CastMovieEntity(
    @SerializedName("cast")
    var cast: List<Cast>? = null,
    @SerializedName("id")
    var id: Int? = null // 524434
) : Parcelable