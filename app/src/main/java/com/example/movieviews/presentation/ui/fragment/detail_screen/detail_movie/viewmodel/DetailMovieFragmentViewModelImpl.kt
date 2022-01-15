package com.example.movieviews.presentation.ui.fragment.detail_screen.detail_movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieviews.data.models.CastEntity
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.data.repository.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailMovieFragmentViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : DetailMovieFragmentViewModel, ViewModel() {

    private val _state = MutableLiveData<DetailMovieViewState>(DetailMovieViewState.Init)
    val state: LiveData<DetailMovieViewState>
        get() = _state

    var id: Int = 0

    override fun getDetailMovie() {
        viewModelScope.launch {
            repositoryDelegate.getMovie()
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e.message)
                }
                .collect { result ->
                    hideLoading()
                    showDetailMovie(result)
                }
        }
    }

    private fun showLoading() {
        _state.value = DetailMovieViewState.Progress(isLoading = true)
    }

    private fun hideLoading() {
        _state.value = DetailMovieViewState.Progress(isLoading = false)
    }

    private fun showMessage(message: String?) {
        if (!message.isNullOrEmpty()) {
            _state.value = DetailMovieViewState.ShowMessage(message)
        }
    }

    private fun showDetailMovie(list: List<MovieEntity>) {
        val movieEntity = list.firstOrNull { it.id == id }
        _state.value = DetailMovieViewState.ShowDetailMovie(
            movieEntity
        )
        showCastMovieList(movieEntity?.cast)
    }

    private fun showCastMovieList(list: List<CastEntity>?) {
        _state.value = DetailMovieViewState.ShowCastMovie(list)
    }
}

interface DetailMovieFragmentViewModel {
    fun getDetailMovie()
}