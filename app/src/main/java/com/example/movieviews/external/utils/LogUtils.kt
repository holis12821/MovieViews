package com.example.movieviews.external.utils

import com.example.movieviews.BuildConfig
import timber.log.Timber

object LogUtils {
    fun error(message: String) {
        if (BuildConfig.DEBUG) {
            Timber.e(message)
        }
    }

    fun info(message: String) {
        if (BuildConfig.DEBUG) {
            Timber.d(message)
        }
    }

    fun print(throwable: Throwable?) {
        if (BuildConfig.DEBUG) {
            throwable?.printStackTrace()
        }
    }
}