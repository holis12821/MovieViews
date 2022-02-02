package com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.external.constant.language
import com.example.movieviews.external.utils.EspressoIdlingResource
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
        viewModelScope.launch {
            repositoryDelegate.getDiscoverTvShow(
                api_key = API_KEY,
                language = language
            ).onStart { showLoading() }
                .catch { e ->
                    showMessage(e)
                }
                .collect { listTvDiscover ->
                    EspressoIdlingResource.decrement()
                    showDiscoverTvShow(listDiscoverTvShow = listTvDiscover)
                }
        }
    }

    private fun showLoading() {
        _state.value = TvShowViewState.Loading
    }

    private fun showMessage(throwable: Throwable) {
        _state.value = TvShowViewState.Message(throwable)
    }

    private fun showDiscoverTvShow(listDiscoverTvShow: List<MovieResult>) {
        _state.value = TvShowViewState.SuccessDiscoverTvShow(listDiscoverTvShow)
    }

}

interface TvShowFragmentViewModel {
    fun getTvShowList()
}