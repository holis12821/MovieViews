package com.example.movieviews.presentation.ui.adapter

import android.view.View
import androidx.fragment.app.Fragment

interface AdapterClickListener<T> {
    fun onItemClickCallback(data: T, fragment: Fragment)
    fun onViewClickCallback(view: View, data: T, fragment: Fragment)
}