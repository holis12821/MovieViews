package com.example.movieviews.presentation.ui.fragment.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.movieviews.data.models.Genre
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.domain.repository.MovieRepository
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.external.constant.language
import com.example.movieviews.external.extension.toJson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieFragmentViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : ViewModel(), MovieFragmentViewModel {

    private val _state = MutableLiveData<MovieViewState>(MovieViewState.Init)
    val state: LiveData<MovieViewState>
        get() = _state

    var currentPage = 1
    var genres: List<Genre>? = null
    var filterGenres = MutableLiveData<List<Genre>>()
    var filterCount = MutableLiveData<Int>()

    override fun getListMovie() {
        val listGenres = arrayListOf<Int>()

        if (!filterGenres.value.isNullOrEmpty()) {
            filterGenres.value?.forEach { genre ->
                genre.id?.let { listGenres.add(it) }
            }
        }

        viewModelScope.launch {
            listGenres.toJson()?.let {
                repositoryDelegate.getDiscoverMovie(currentPage, it)
                    .onStart { showLoading() }
                    .catch { e ->
                        hideLoading()
                        showMessage(e)
                    }
                    .collect { pagingData ->
                        hideLoading()
                        showDiscoverMovie(pagingData = pagingData)
                    }
            }
        }
    }

    override fun getGenres() {
        viewModelScope.launch {
            repositoryDelegate.getGenres(
                apiKey = API_KEY,
                language = language
            ).catch { e ->
                showMessage(e)
            }
                .collect { genres ->
                    showGenres(genres = genres)
                }
        }
    }

    private fun showLoading() {
        _state.postValue(MovieViewState.Loading)
    }

    private fun hideLoading() {
        _state.value = MovieViewState.HideLoading
    }

    private fun showMessage(throwable: Throwable) {
        _state.value = MovieViewState.Message(throwable)
    }

    private fun showDiscoverMovie(pagingData: PagingData<MovieResult>) {
        _state.postValue(MovieViewState.SuccessDiscoverMovie(pagingData = pagingData))
    }

    private fun showGenres(genres: List<Genre>) {
        _state.value = MovieViewState.ShowGenres(genres = genres)
    }
}


interface MovieFragmentViewModel {
    fun getListMovie()
    fun getGenres()
}