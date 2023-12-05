package com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel

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

class TvShowFragmentViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : ViewModel(), TvShowFragmentViewModel {

    private val _state = MutableLiveData<TvShowViewState>(TvShowViewState.Init)
    val state: LiveData<TvShowViewState>
        get() = _state

    override fun getTvShowList() {
        viewModelScope.launch {
            repositoryDelegate.getDiscoverTvShow()
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e)
                }
                .collect { result ->
                    hideLoading()
                    showDiscoverTvShow(result)
                }
        }
    }

    private fun showLoading() {
        _state.value = TvShowViewState.Loading
    }

    private fun hideLoading() {
        _state.value = TvShowViewState.HideLoading
    }

    private fun showMessage(throwable: Throwable) {
        _state.value = TvShowViewState.Message(throwable)
    }

    private fun showDiscoverTvShow(pagingData: PagingData<MovieResult>) {
        _state.value = TvShowViewState.SuccessDiscoverTvShow(pagingData)
    }

}

interface TvShowFragmentViewModel {
    fun getTvShowList()
}