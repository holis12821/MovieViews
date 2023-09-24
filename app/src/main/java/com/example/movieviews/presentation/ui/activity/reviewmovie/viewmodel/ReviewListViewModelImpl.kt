package com.example.movieviews.presentation.ui.activity.reviewmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.movieviews.data.models.Review
import com.example.movieviews.domain.repository.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ReviewListViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : ViewModel(), ReviewListViewModel {

    private val _state = MutableLiveData<ReviewListViewState>(ReviewListViewState.Init)
    val state: LiveData<ReviewListViewState> get() = _state

    var movieId: Int? = null

    override fun getReviewList() {
        viewModelScope.launch {
            viewModelScope.launch {
                movieId?.let {
                    repositoryDelegate.getReviewMoviePagingSource(it)
                        .onStart { showLoading() }
                        .catch { e ->
                            hideLoading()
                            showMessage(e)
                        }
                        .collect { pagingData ->
                            hideLoading()
                            showReviewList(pagingData = pagingData)
                        }
                }
            }
        }
    }

    private fun showLoading() {
        _state.value = ReviewListViewState.Loading
    }

    private fun hideLoading() {
        _state.value = ReviewListViewState.HideLoading
    }

    private fun showMessage(throwable: Throwable) {
        _state.value = ReviewListViewState.Message(throwable)
    }

    private fun showReviewList(pagingData: PagingData<Review>) {
        _state.postValue(ReviewListViewState.ShowReviewList(pagingData = pagingData))
    }

}

interface ReviewListViewModel {
    fun getReviewList()
}