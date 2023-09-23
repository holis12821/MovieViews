package com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieviews.core.BaseViewModel
import com.example.movieviews.data.models.Cast
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.models.Video
import com.example.movieviews.domain.repository.MovieRepository
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.external.constant.language
import com.example.movieviews.external.utils.EspressoIdlingResource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailMovieActivityViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : DetailMovieActivityViewModel, BaseViewModel() {

    private val _state = MutableLiveData<DetailMovieViewState>(DetailMovieViewState.Init)
    val state: LiveData<DetailMovieViewState>
        get() = _state

    var detailMovieFlags = false
    var movieId: Int = 0
    var tvShowId: Int = 0

    override fun getDetailMovie() {
        viewModelScope.launch {
            repositoryDelegate.getDetailMovie(
                movieId = movieId,
                apiKey = API_KEY
            ).onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e)
                }
                .collect { detailMovie ->
                    hideLoading()
                    EspressoIdlingResource.decrement()
                    showDetailMovie(detailMovie)
                }
        }
    }

    override fun getDetailTvShow() {
        viewModelScope.launch {
            repositoryDelegate.getDetailTvShow(
                tvId = tvShowId,
                apiKey = API_KEY
            ).onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e)
                }
                .collect { detailTvShow ->
                    hideLoading()
                    EspressoIdlingResource.decrement()
                    showDetailMovie(detailTvShow)
                }
        }
    }

    override fun getCastMovie() {
        viewModelScope.launch {
            repositoryDelegate.getCreditsMovie(
                movieId = movieId,
                apiKey = API_KEY,
                language = language
            ).onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e)
                }
                .collect { creditMovie ->
                    hideLoading()
                    EspressoIdlingResource.decrement()
                    showCastMovieList(creditMovie)
                }
        }
    }

    override fun getVideoMovie() {
        viewModelScope.launch {
            repositoryDelegate.getVideo(
                movieId = movieId,
                apiKey = API_KEY,
                language = language
            ).catch { e->
                showMessage(e)
            }
                .collect { videos ->
                    showVideo(videos)
                }
        }
    }

    override fun showLoading() {
        _state.value = DetailMovieViewState.Loading
    }

    override fun hideLoading() {
        _state.value = DetailMovieViewState.HideLoading
    }

    override fun showMessage(throwable: Throwable) {
        _state.value = DetailMovieViewState.Message(throwable)
    }

    private fun showDetailMovie(detailMovie: MovieResult) {
        _state.value = DetailMovieViewState.ShowDetailMovie(detailMovie)
    }

    private fun showCastMovieList(listCast: List<Cast>) {
        _state.value = DetailMovieViewState.ShowCastMovie(listCast)
    }

    private fun showVideo(videos: List<Video>) {
        _state.value = DetailMovieViewState.ShowVideo(videos)
    }
}

interface DetailMovieActivityViewModel {
    fun getDetailMovie()
    fun getDetailTvShow()
    fun getCastMovie()
    fun getVideoMovie()
}