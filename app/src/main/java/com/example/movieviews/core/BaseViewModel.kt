package com.example.movieviews.core

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.movieviews.data.models.MovieResult
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel: ViewModel() {
    abstract fun showLoading()
    abstract fun hideLoading()
    abstract fun showMessage(throwable: Throwable)
}