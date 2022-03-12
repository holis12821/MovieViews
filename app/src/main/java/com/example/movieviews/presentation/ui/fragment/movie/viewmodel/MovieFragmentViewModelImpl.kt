package com.example.movieviews.presentation.ui.fragment.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.domain.repository.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieFragmentViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : ViewModel(), MovieFragmentViewModel {

    private val _state = MutableLiveData<MovieViewState>(MovieViewState.Init)
    val state: LiveData<MovieViewState>
        get() = _state

    override fun getListMovie() {
        viewModelScope.launch {
            repositoryDelegate.getDiscoverMovie()
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e)
                }
                .collect {  pagingData ->
                    hideLoading()
                    showDiscoverMovie(pagingData = pagingData)
                }
        }
    }

    private fun showLoading() {
        _state.postValue(MovieViewState.Loading)
    }

    private fun hideLoading() {
        _state.value = MovieViewState.HideLoading
    }

    private fun showMessage(throwable: Throwable) {
        _state.value = MovieViewState.Message(throwable)
    }

    private fun showDiscoverMovie(pagingData: PagingData<MovieResult>) {
        _state.postValue(MovieViewState.SuccessDiscoverMovie(pagingData = pagingData))
    }
}


interface MovieFragmentViewModel {
    fun getListMovie()
}