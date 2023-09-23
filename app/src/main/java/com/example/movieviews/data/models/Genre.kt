package com.example.movieviews.data.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

@Parcelize
data class Genre(
    var isChecked: Boolean = false,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,

) : Parcelable {
    companion object {
        fun objectFromData(str: String?): Genre? {
            return try {
                return Gson().fromJson(str, Genre::class.java)
            }catch (e : Exception){
                null
            }
        }

        fun arrayFromData(str: String?): List<Genre>? {
            return try {
                val listType = object : TypeToken<ArrayList<Genre>?>() {}.type
                Gson().fromJson(str, listType)
            }catch (e : Exception){
                null
            }
        }
    }
}