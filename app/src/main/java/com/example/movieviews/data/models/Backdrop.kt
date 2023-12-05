package com.example.movieviews.data.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Backdrop(
    @SerializedName("aspect_ratio")
    var aspectRatio: Double? = null, // 1.778
    @SerializedName("file_path")
    var filePath: String? = null, // /d8duYyyC9J5T825Hg7grmaabfxQ.jpg
    @SerializedName("height")
    var height: Int? = null, // 1080
    @SerializedName("vote_average")
    var voteAverage: Double? = null, // 5.512
    @SerializedName("vote_count")
    var voteCount: Int? = null, // 29
    @SerializedName("width")
    var width: Int? = null // 1920
) : Parcelable