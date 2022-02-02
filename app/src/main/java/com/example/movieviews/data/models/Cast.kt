package com.example.movieviews.data.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Cast(
    @SerializedName("adult")
    var adult: Boolean? = null, // false
    @SerializedName("cast_id")
    var castId: Int? = null, // 182
    @SerializedName("character")
    var character: String? = null, // Sersi
    @SerializedName("credit_id")
    var creditId: String? = null, // 6189625f1fd36f0026d361cb
    @SerializedName("gender")
    var gender: Int? = null, // 1
    @SerializedName("id")
    var id: Int? = null, // 97576
    @SerializedName("known_for_department")
    var knownForDepartment: String? = null, // Acting
    @SerializedName("name")
    var name: String? = null, // Gemma Chan
    @SerializedName("order")
    var order: Int? = null, // 0
    @SerializedName("original_name")
    var originalName: String? = null, // Gemma Chan
    @SerializedName("popularity")
    var popularity: Double? = null, // 36.178
    @SerializedName("profile_path")
    var profilePath: String? = null // /stTKj4iNauhqlVmZ6XAsFsvcMCY.jpg
) : Parcelable