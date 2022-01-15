package com.example.movieviews.presentation.ui.fragment.detail_screen.detail_movie.viewmodel

import com.example.movieviews.data.models.MovieEntity

sealed class DetailMovieViewState {
    object Init: DetailMovieViewState()
    data class Progress(val isLoading: Boolean): DetailMovieViewState()
    data class ShowMessage(val message: String): DetailMovieViewState()
    data class ShowDetailMovie(val detailMovieEntity: MovieEntity?): DetailMovieViewState()
}
