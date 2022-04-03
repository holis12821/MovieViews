package com.example.movieviews.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.movieviews.external.utils.NetworkUtils
import com.example.movieviews.external.utils.callback.NetworkChangeListener

class NetworkChangeReceiver : BroadcastReceiver() {

    var callBack: NetworkChangeListener? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            callBack?.onNetworkChanged()
        }
    }
}