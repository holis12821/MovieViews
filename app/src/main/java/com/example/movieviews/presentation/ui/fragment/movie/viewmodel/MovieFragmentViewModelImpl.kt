package com.example.movieviews.presentation.ui.fragment.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.data.repository.MovieRepository
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
            repositoryDelegate.getMovie()
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e.message)
                }
                .collect { result ->
                    hideLoading()
                    showListMovie(result)
                }
        }
    }

    private fun showLoading() {
        _state.value = MovieViewState.Progress(isLoading = true)
    }

    private fun hideLoading() {
        _state.value = MovieViewState.Progress(isLoading = false)
    }

    private fun showMessage(message: String?) {
        if (!message.isNullOrEmpty()) {
            _state.value = MovieViewState.ShowMessage(message)
        }
    }

    private fun showListMovie(list: List<MovieEntity>) {
        _state.value = MovieViewState.ShowMovie(list = list)
    }
}


interface MovieFragmentViewModel {
    fun getListMovie()
}