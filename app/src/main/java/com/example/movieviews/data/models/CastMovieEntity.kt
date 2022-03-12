package com.example.movieviews.data.models


import com.google.gson.annotations.SerializedName

data class CastMovieEntity(
    @SerializedName("cast")
    var cast: List<Cast>? = null,
    @SerializedName("id")
    var id: Int? = null // 524434
)