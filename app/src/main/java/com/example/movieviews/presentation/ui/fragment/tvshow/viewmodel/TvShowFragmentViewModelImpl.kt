package com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.dumydata.DataMovieDummy
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class TvShowFragmentViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : TvShowFragmentViewModel, ViewModel() {

    private val _state = MutableLiveData<TvShowViewState>(TvShowViewState.Init)
    val state: LiveData<TvShowViewState>
        get() = _state

    override fun getTvShowList() {
        val movieList = DataMovieDummy.getMovies()
        viewModelScope.launch {
            repositoryDelegate.getMovie(movieList = movieList)
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e.message)
                }
                .collect { result ->
                    hideLoading()
                    showTvShow(result)
                }
        }
    }

    private fun showLoading() {
        _state.value = TvShowViewState.Progress(isLoading = true)
    }

    private fun hideLoading() {
        _state.value = TvShowViewState.Progress(isLoading = false)
    }

    private fun showMessage(message: String?) {
        if (!message.isNullOrEmpty()) {
            _state.value = TvShowViewState.ShowMessage(message)
        }
    }

    private fun showTvShow(list: List<MovieEntity>) {
        _state.value = TvShowViewState.ShowTvShow(list = list)
    }

}

interface TvShowFragmentViewModel {
    fun getTvShowList()
}