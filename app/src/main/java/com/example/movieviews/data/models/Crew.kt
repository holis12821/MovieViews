package com.example.movieviews.data.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Crew(
    @SerializedName("adult")
    var adult: Boolean? = null, // false
    @SerializedName("credit_id")
    var creditId: String? = null, // 5d49cb99028f144b8f00c7ee
    @SerializedName("department")
    var department: String? = null, // Editing
    @SerializedName("gender")
    var gender: Int? = null, // 2
    @SerializedName("id")
    var id: Int? = null, // 1722
    @SerializedName("job")
    var job: String? = null, // Editor
    @SerializedName("known_for_department")
    var knownForDepartment: String? = null, // Editing
    @SerializedName("name")
    var name: String? = null, // Craig Wood
    @SerializedName("original_name")
    var originalName: String? = null, // Craig Wood
    @SerializedName("popularity")
    var popularity: Double? = null, // 0.6
) : Parcelable