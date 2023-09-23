package com.example.movieviews.data.models

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Sort(
    var isChecked: Boolean = false,
    var id: Int? = null,
    var value: String? = null,
    var title: String? = null
) {
    companion object {
        fun arrayFromData(str: String?): List<Sort>? {
            return try {
                val listType = object : TypeToken<ArrayList<Sort>?>() {}.type
                Gson().fromJson(str, listType)
            }catch (e : Exception){
                null
            }
        }
    }
}
