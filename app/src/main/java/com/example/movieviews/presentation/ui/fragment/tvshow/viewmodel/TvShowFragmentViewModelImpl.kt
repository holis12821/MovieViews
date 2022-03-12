package com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.domain.repository.MovieRepository

class TvShowFragmentViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : ViewModel(), TvShowFragmentViewModel {

    private val _state = MutableLiveData<TvShowViewState>(TvShowViewState.Init)
    val state: LiveData<TvShowViewState>
        get() = _state

    override fun getTvShowList() {

    }

    private fun showLoading() {
        _state.postValue(TvShowViewState.Loading)
    }

    private fun hideLoading() {
        _state.value = TvShowViewState.HideLoading
    }

    private fun showMessage(throwable: Throwable) {
        _state.value = TvShowViewState.Message(throwable)
    }

    private fun showDiscoverTvShow(listDiscoverTvShow: List<MovieResult>?) {
        _state.postValue(TvShowViewState.SuccessDiscoverTvShow(listDiscoverTvShow))
    }

}

interface TvShowFragmentViewModel {
    fun getTvShowList()
}