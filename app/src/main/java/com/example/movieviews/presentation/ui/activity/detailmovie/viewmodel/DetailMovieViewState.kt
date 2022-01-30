package com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel

import com.example.movieviews.data.models.CastEntity
import com.example.movieviews.data.models.MovieEntity

sealed class DetailMovieViewState {
    object Init: DetailMovieViewState()
    data class Progress(val isLoading: Boolean): DetailMovieViewState()
    data class ShowMessage(val message: String): DetailMovieViewState()
    data class ShowDetailMovie(val detailMovieEntity: MovieEntity?): DetailMovieViewState()
    data class ShowCastMovie(val detailMovieEntity: List<CastEntity>?): DetailMovieViewState()
}
