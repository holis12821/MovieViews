package com.example.movieviews.presentation.ui.fragment.movie.viewmodel

import androidx.paging.PagingData
import com.example.movieviews.data.models.MovieResult

sealed class MovieViewState {
    object Init: MovieViewState()
    object Loading: MovieViewState()
    object HideLoading: MovieViewState()
    data class Message(val throwable: Throwable): MovieViewState()
    data class SuccessDiscoverMovie(val pagingData: PagingData<MovieResult>? = null): MovieViewState()
}
