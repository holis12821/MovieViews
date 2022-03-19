package com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel

import com.example.movieviews.data.models.Cast
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeViewState

sealed class DetailMovieViewState {
    object Init: DetailMovieViewState()
    object Loading: DetailMovieViewState()
    object HideLoading: DetailMovieViewState()
    data class Message(val throwable: Throwable): DetailMovieViewState()
    data class ShowDetailMovie(val detailMovieEntity: MovieResult): DetailMovieViewState()
    data class ShowCastMovie(val listCastMovie: List<Cast>): DetailMovieViewState()


}
