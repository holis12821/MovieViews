package com.example.movieviews.data.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Poster(
    @SerializedName("aspect_ratio")
    var aspectRatio: Double? = null, // 0.667
    @SerializedName("file_path")
    var filePath: String? = null, // /tdQzRSk4PXX6hzjLcQWHafYtZTI.jpg
    @SerializedName("height")
    var height: Int? = null, // 3000
    @SerializedName("iso_639_1")
    var iso6391: String? = null, // en
    @SerializedName("vote_average")
    var voteAverage: Double? = null, // 5.454
    @SerializedName("vote_count")
    var voteCount: Int? = null, // 3
    @SerializedName("width")
    var width: Int? = null // 2000
) : Parcelable