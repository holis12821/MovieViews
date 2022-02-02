package com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel

import com.example.movieviews.data.local.CastEntity
import com.example.movieviews.data.local.MovieEntity
import com.example.movieviews.data.models.Cast
import com.example.movieviews.data.models.DetailMovieEntity

sealed class DetailMovieViewState {
    object Init: DetailMovieViewState()
    object Loading: DetailMovieViewState()
    data class Message(val throwable: Throwable): DetailMovieViewState()
    data class ShowDetailMovie(val detailMovieEntity: DetailMovieEntity): DetailMovieViewState()
    data class ShowCastMovie(val listCastMovie: List<Cast>): DetailMovieViewState()
}
