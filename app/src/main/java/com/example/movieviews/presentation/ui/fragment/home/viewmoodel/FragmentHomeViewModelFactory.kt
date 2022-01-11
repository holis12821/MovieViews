package com.example.movieviews.presentation.ui.fragment.home.viewmoodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieviews.domain.repository.MovieRepository

@Suppress("UNCHECKED_CAST")
class FragmentHomeViewModelFactory(
    private val repositoryDelegate: MovieRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = FragmentHomeViewModelImpl(repositoryDelegate) as T
}