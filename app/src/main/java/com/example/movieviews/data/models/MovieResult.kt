package com.example.movieviews.data.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResult(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("status_code")
    var status_code: Int? = 0,
    @SerializedName("status_message")
    var status_message: String? = "",
    @SerializedName("success")
    var success: Boolean? = false,
    @SerializedName("adult")
    var adult: Boolean? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("budget")
    var budget: Long? = 0,
    @SerializedName("genres")
    var genres: List<Genre>? = null,
    @SerializedName("homepage")
    var homepage: String? = "",
    @SerializedName("imdb_id")
    var imdbId: String? = "",
    @SerializedName("original_language")
    var originalLanguage: String? = null,
    @SerializedName("original_name")
    var originalName: String? = null,
    @SerializedName("original_title")
    var originalTitle: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("popularity")
    var popularity: Double? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    @SerializedName("revenue")
    var revenue: Long? = 0,
    @SerializedName("runtime")
    var runtime: Int? = 0,
    @SerializedName("status")
    var status: String? = "",
    @SerializedName("tagline")
    var tagline: String? = "",
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("video")
    var video: Boolean? = null,
    @SerializedName("vote_average")
    var voteAverage: Double? = null,
    @SerializedName("vote_count")
    var voteCount: Long? = null
) : Parcelable