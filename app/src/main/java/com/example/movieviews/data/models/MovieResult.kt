package com.example.movieviews.data.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
@Entity(tableName = "MovieResult")
data class MovieResult(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("adult")
    var adult: Boolean? = null,
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null,
    @SerializedName("name")
    var name: String? = null,
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