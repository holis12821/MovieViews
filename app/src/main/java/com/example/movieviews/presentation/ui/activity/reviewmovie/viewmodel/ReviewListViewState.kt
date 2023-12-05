package com.example.movieviews.presentation.ui.activity.reviewmovie.viewmodel

import androidx.paging.PagingData
import com.example.movieviews.data.models.Review

sealed class ReviewListViewState {
    data object Init: ReviewListViewState()
    data object Loading: ReviewListViewState()
    data object HideLoading: ReviewListViewState()
    data class Message(val throwable: Throwable): ReviewListViewState()
    data class ShowReviewList(val pagingData: PagingData<Review>? = null): ReviewListViewState()
}
