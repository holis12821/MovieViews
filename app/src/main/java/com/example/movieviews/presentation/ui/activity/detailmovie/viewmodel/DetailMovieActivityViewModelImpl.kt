package com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieviews.data.models.Cast
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.domain.repository.MovieRepository

class DetailMovieActivityViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : DetailMovieActivityViewModel, ViewModel() {

    private val _state = MutableLiveData<DetailMovieViewState>(DetailMovieViewState.Init)
    val state: LiveData<DetailMovieViewState>
        get() = _state

    var movieId: Int = 0
    var tvShowId: Int = 0

    override fun getDetailMovie() {

    }

    override fun getDetailTvShow() {

    }

    override fun getCastMovie() {

    }

    private fun showLoading() {
        _state.value = DetailMovieViewState.Loading
    }

    private fun showMessage(throwable: Throwable) {
        _state.value = DetailMovieViewState.Message(throwable)
    }

    private fun showDetailMovie(detailMovie: MovieResult) {
        _state.value = DetailMovieViewState.ShowDetailMovie(detailMovie)
    }

    private fun showCastMovieList(listCast: List<Cast>) {
        _state.value = DetailMovieViewState.ShowCastMovie(listCast)
    }
}

interface DetailMovieActivityViewModel {
    fun getDetailMovie()
    fun getDetailTvShow()
    fun getCastMovie()
}