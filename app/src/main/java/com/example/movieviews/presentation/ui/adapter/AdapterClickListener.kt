package com.example.movieviews.presentation.ui.adapter

import android.view.View
import androidx.fragment.app.Fragment

interface AdapterClickListener<T> {
    fun onItemCLickCallback(data: T, fragment: Fragment)
    fun onViewCClickCallback(view: View, data: T, fragment: Fragment)
}