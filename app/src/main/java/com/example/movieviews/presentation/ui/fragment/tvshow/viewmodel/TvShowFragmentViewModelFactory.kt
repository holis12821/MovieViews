package com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieviews.data.repository.MovieRepository

@Suppress("UNCHECKED_CAST")
class TvShowFragmentViewModelFactory(
    private val repositoryDelegate: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        TvShowFragmentViewModelImpl(repositoryDelegate) as T
}