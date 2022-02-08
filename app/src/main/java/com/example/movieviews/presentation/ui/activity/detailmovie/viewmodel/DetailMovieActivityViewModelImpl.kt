package com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieviews.data.models.Cast
import com.example.movieviews.data.models.DetailMovieEntity
import com.example.movieviews.data.repository.remote.MovieRepository
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.external.constant.language
import com.example.movieviews.external.utils.EspressoIdlingResource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailMovieActivityViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : DetailMovieActivityViewModel, ViewModel() {

    private val _state = MutableLiveData<DetailMovieViewState>(DetailMovieViewState.Init)
    val state: LiveData<DetailMovieViewState>
        get() = _state

    var movieId: Int = 0
    var tvShowId: Int = 0

    override fun getDetailMovie() {
        viewModelScope.launch {
            repositoryDelegate.getDetailMovie(
                movie_id = movieId,
                api_key = API_KEY
            ).onStart { showLoading() }
                .catch { e ->
                    showMessage(e)
                }
                .collect { detailMovie ->
                    EspressoIdlingResource.decrement()
                    showDetailMovie(detailMovie)
                }
        }
    }

    override fun getDetailTvShow() {
        viewModelScope.launch {
            repositoryDelegate.getDetailTvShow(
                tv_id = tvShowId,
                api_key = API_KEY
            ).onStart { showLoading() }
                .catch { e ->
                    showMessage(e)
                }
                .collect { detailTvShow ->
                    EspressoIdlingResource.decrement()
                    showDetailMovie(detailTvShow)
                }
        }
    }

    override fun getCastMovie() {
        viewModelScope.launch {
            repositoryDelegate.getCreditsMovie(
                movie_id = movieId,
                api_key = API_KEY,
                language = language
            ).onStart { showLoading() }
                .catch { e ->
                    showMessage(e)
                }
                .collect { listCreditCast ->
                    EspressoIdlingResource.decrement()
                    showCastMovieList(listCast = listCreditCast)
                }
        }
    }

    private fun showLoading() {
        _state.value = DetailMovieViewState.Loading
    }

    private fun showMessage(throwable: Throwable) {
        _state.value = DetailMovieViewState.Message(throwable)
    }

    private fun showDetailMovie(detailMovie: DetailMovieEntity) {
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