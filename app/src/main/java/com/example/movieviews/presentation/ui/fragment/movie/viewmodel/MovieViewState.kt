package com.example.movieviews.presentation.ui.fragment.movie.viewmodel

import com.example.movieviews.data.models.MovieEntity

sealed class MovieViewState {
    object Init: MovieViewState()
    data class Progress(val isLoading: Boolean): MovieViewState()
    data class ShowMessage(val message: String): MovieViewState()
    data class ShowMovie(val list: List<MovieEntity>): MovieViewState()
}
