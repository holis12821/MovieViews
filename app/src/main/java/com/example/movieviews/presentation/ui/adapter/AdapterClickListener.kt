package com.example.movieviews.presentation.ui.adapter

import android.view.View
import androidx.fragment.app.Fragment

interface AdapterClickListener<T> {
    fun onItemClickCallback(data: T)
    fun onViewClickCallback(view: View, data: T)
}