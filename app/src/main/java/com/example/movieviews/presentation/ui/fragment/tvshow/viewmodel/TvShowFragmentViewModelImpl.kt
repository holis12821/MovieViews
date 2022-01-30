package com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.data.repository.MovieRepository

class TvShowFragmentViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : TvShowFragmentViewModel, ViewModel() {

    private val _state = MutableLiveData<List<MovieEntity>>()
    val state: LiveData<List<MovieEntity>>
        get() = _state

    override fun getTvShowList() {
        _state.value = repositoryDelegate.getMovie()
    }
}

interface TvShowFragmentViewModel {
    fun getTvShowList()
}