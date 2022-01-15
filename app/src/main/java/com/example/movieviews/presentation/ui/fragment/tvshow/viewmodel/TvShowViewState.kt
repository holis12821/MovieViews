package com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel

import com.example.movieviews.data.models.MovieEntity

sealed class TvShowViewState {
   object Init : TvShowViewState()
   data class Progress(val isLoading: Boolean): TvShowViewState()
   data class ShowMessage(val message: String): TvShowViewState()
   data class ShowTvShow(val list: List<MovieEntity>): TvShowViewState()
}
