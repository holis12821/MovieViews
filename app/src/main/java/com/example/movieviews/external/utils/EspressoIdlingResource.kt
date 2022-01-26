package com.example.movieviews.external.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val idlingResource = CountingIdlingResource(RESOURCE)

    val getIdlingResource: IdlingResource
        get() = idlingResource

    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        idlingResource.decrement()
    }
}