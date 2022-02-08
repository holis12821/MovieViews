package com.example.movieviews.data.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Parcelize
data class DetailMovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("status_code")
    var status_code: Int? = 0,
    @SerializedName("status_message")
    var status_message: String? = null,
    @SerializedName("success")
    var success: Boolean? = false,
    @SerializedName("adult")
    var adult: Boolean? = null,
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("budget")
    var budget: Int? = null,
    @ColumnInfo(name = "genres")
    @SerializedName("genres")
    var genres: List<Genre>? = null,
    @SerializedName("homepage")
    var homepage: String? = null,
    @SerializedName("imdb_id")
    var imdbId: String? = null,
    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    var originalLanguage: String? = null,
    @ColumnInfo(name = "original_name")
    @SerializedName("original_name")
    var originalName: String? = null,
    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    var originalTitle: String? = null,
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("popularity")
    var popularity: Double? = null,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    var releaseDate: String? = null,
    @SerializedName("revenue")
    var revenue: Int? = null,
    @SerializedName("runtime")
    var runtime: Int? = null,
    @SerializedName("status")
    var status: String? = null,
    @ColumnInfo(name = "tagline")
    @SerializedName("tagline")
    var tagline: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("video")
    var video: Boolean? = null,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var voteAverage: Double? = null,
    @SerializedName("vote_count")
    var voteCount: Int? = null
) : Parcelable