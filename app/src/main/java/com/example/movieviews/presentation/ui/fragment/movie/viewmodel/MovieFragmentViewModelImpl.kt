package com.example.movieviews.presentation.ui.fragment.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.repository.remote.MovieRepository
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.external.constant.language
import com.example.movieviews.external.utils.EspressoIdlingResource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieFragmentViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : MovieFragmentViewModel, ViewModel() {

    private val _state = MutableLiveData<MovieViewState>(MovieViewState.Init)
    val state: LiveData<MovieViewState>
        get() = _state

    override fun getListMovie() {
        viewModelScope.launch {
            repositoryDelegate.getDiscoverMovie(
                api_key = API_KEY,
                language = language
            ).onStart { showLoading() }
                .catch { e ->
                    showMessage(e)
                }
                .collect { listDiscoverMovie ->
                    EspressoIdlingResource.decrement()
                    showDiscoverMovie(listMovieDiscover = listDiscoverMovie)
                }
        }
    }

    private fun showLoading() {
        _state.value = MovieViewState.Loading
    }

    private fun showMessage(throwable: Throwable) {
        _state.value = MovieViewState.Message(throwable)
    }

    private fun showDiscoverMovie(listMovieDiscover: List<MovieResult>) {
        _state.value = MovieViewState.SuccessDiscoverMovie(listMovieDiscover)
    }
}


interface MovieFragmentViewModel {
    fun getListMovie()
}