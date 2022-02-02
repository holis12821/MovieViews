package com.example.movieviews.data.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ImageCollection(
    @SerializedName("backdrops")
    var backdrops: List<Backdrop>? = null,
    @SerializedName("id")
    var id: Int? = null, // 10
    @SerializedName("posters")
    var posters: List<Poster>? = null
) : Parcelable