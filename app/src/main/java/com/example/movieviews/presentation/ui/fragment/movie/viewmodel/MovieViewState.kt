package com.example.movieviews.presentation.ui.fragment.movie.viewmodel

import com.example.movieviews.data.models.MovieResult

sealed class MovieViewState {
    object Init: MovieViewState()
    object Loading: MovieViewState()
    data class Message(val throwable: Throwable): MovieViewState()
    data class SuccessDiscoverMovie(val listMovieDiscoverMovie: List<MovieResult>): MovieViewState()
}
