package com.example.movieviews.external.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object NetworkUtils {
    @Suppress("DEPRECATION")
    fun isNetworkAvailable(context: Context?): Boolean {
       val cm =
           context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = cm.activeNetwork
            val capabilities = cm.getNetworkCapabilities(activeNetwork)
            return if (capabilities != null) {
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
            } else false
        } else {
            cm.activeNetworkInfo?.let {
                return it.isConnectedOrConnecting
            }
        }
        return false
    }
}