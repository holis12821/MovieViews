package com.example.movieviews.presentation.ui.fragment.home.viewmodel

import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.models.Poster

sealed class HomeViewState {
    object Init: HomeViewState()
    object Loading: HomeViewState()
    object HideLoading: HomeViewState()
    data class Message(val throwable: Throwable): HomeViewState()
    data class PosterMovie(val listPoster: List<Poster>?): HomeViewState()
    data class SuccessPopularMovie(val listPopularMovie: List<MovieResult>?): HomeViewState()
    data class SuccessTopRatedMovie(val listTopRatedMovie: List<MovieResult>?): HomeViewState()
    data class SuccessTrendingMovie(val listTrendingMovie: List<MovieResult>?): HomeViewState()
    data class SuccessUpcomingMovie(val listUpcomingMovie: List<MovieResult>?): HomeViewState()
}
