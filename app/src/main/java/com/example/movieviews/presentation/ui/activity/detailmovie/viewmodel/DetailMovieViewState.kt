package com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel

import com.example.movieviews.data.models.Cast
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.models.Review
import com.example.movieviews.data.models.Video

sealed class DetailMovieViewState {
    data object Init: DetailMovieViewState()
    data object Loading: DetailMovieViewState()
    data object HideLoading: DetailMovieViewState()
    data class Message(val throwable: Throwable): DetailMovieViewState()
    data class ShowDetailMovie(val detailMovieEntity: MovieResult): DetailMovieViewState()
    data class ShowCastMovie(val listCastMovie: List<Cast>): DetailMovieViewState()
    data class ShowVideo(val videos: List<Video>?) : DetailMovieViewState()
    data class ShowReview(val review: List<Review>?): DetailMovieViewState()
}
