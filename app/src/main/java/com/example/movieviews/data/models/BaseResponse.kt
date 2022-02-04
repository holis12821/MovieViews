package com.example.movieviews.data.models

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("status_code")
    var status_code: Int? = 0,
    @SerializedName("status_message")
    var status_message: String? = null,
    @SerializedName("success")
    var success: Boolean? = false,
    @SerializedName("page")
    var page: Int? = 0,
    @SerializedName("results")
    var results: T? = null,
    @SerializedName("total_pages")
    var total_pages: Long? = 0,
    @SerializedName("total_results")
    var total_results: Long? = 0
)