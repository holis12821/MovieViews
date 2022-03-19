package com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel

import androidx.paging.PagingData
import com.example.movieviews.data.models.MovieResult

sealed class TvShowViewState {
    object Init: TvShowViewState()
    object Loading: TvShowViewState()
    object HideLoading: TvShowViewState()
    data class Message(val throwable: Throwable): TvShowViewState()
    data class SuccessDiscoverTvShow(val data: PagingData<MovieResult>? = null): TvShowViewState()
}
