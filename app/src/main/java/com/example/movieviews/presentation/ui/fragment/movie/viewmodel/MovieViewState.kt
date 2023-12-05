package com.example.movieviews.presentation.ui.fragment.movie.viewmodel

import androidx.paging.PagingData
import com.example.movieviews.data.models.Genre
import com.example.movieviews.data.models.MovieResult

sealed class MovieViewState {
    data object Init: MovieViewState()
    data object Loading: MovieViewState()
    data object HideLoading: MovieViewState()
    data class Message(val throwable: Throwable): MovieViewState()
    data class SuccessDiscoverMovie(val pagingData: PagingData<MovieResult>? = null): MovieViewState()
    data class ShowGenres(val genres: List<Genre>): MovieViewState()
}
