package com.example.movieviews.presentation.ui.fragment.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.dumydata.DataMovieDummy
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeFragmentViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : ViewModel(), HomeFragmentViewModel {

    private val _state = MutableLiveData<HomeViewState>(HomeViewState.Init)
    val state: LiveData<HomeViewState>
        get() = _state

    override fun getMovie() {
        viewModelScope.launch {
            val movieList = DataMovieDummy.getMovies()
            repositoryDelegate.getMovie(movieList = movieList)
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e.message)
                }
                .collect { result ->
                    hideLoading()
                    showMovie(result)
                }
        }
    }

   private fun showLoading() {
       _state.value = HomeViewState.Progress(isLoading = true)
   }

   private fun hideLoading() {
       _state.value = HomeViewState.Progress(isLoading = false)
   }

   private fun showMessage(message: String?) {
       if (!message.isNullOrEmpty()) {
           _state.value = HomeViewState.ShowMessage(message)
       }
   }

   private fun showMovie(list: List<MovieEntity>) {
       _state.value = HomeViewState.ShowMovie(list = list)
   }
}

interface HomeFragmentViewModel {
    fun getMovie()
}
