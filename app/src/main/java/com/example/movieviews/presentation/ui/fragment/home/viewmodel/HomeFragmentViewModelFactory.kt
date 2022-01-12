package com.example.movieviews.presentation.ui.fragment.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieviews.data.repository.MovieRepository

@Suppress("UNCHECKED_CAST")
class HomeFragmentViewModelFactory(
    private val repositoryDelegate: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        HomeFragmentViewModelImpl(repositoryDelegate) as T
}