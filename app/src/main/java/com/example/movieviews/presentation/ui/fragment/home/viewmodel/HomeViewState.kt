package com.example.movieviews.presentation.ui.fragment.home.viewmodel

import com.example.movieviews.data.models.MovieEntity

sealed class HomeViewState {
    object Init : HomeViewState()
    data class Progress(val isLoading: Boolean) : HomeViewState()
    data class ShowMessage(val message: String) : HomeViewState()
    data class ShowMovie(val list: List<MovieEntity>) : HomeViewState()
}
