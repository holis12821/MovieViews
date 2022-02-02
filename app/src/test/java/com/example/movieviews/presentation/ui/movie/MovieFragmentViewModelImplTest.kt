package com.example.movieviews.presentation.ui.movie

import androidx.lifecycle.Observer
import com.example.movieviews.data.local.MovieEntity
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.dumydata.DataMovieDummy
import com.example.movieviews.presentation.ui.fragment.movie.viewmodel.MovieFragmentViewModelImpl
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoMoreInteractions


@RunWith(MockitoJUnitRunner::class)
class MovieFragmentViewModelImplTest {
    //mock object
    private val mMovieRepository = mock<MovieRepository>()
    private val mMovieFragmentViewModel = MovieFragmentViewModelImpl(mMovieRepository)

    //observer is A simple callback that
    //can receive from LiveData. and onChange to trigger change to UI
    private val mObserver = mock<Observer<List<MovieEntity>>>()

    private val movieList = DataMovieDummy.getMovies()

    /**
     * Things to prepare before the test takes place
     * */
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        mMovieFragmentViewModel.state.observeForever(mObserver)
    }

    @Test
    fun getListMovie() {

    }


    @After
    fun tearDown() {

        verifyNoMoreInteractions(mMovieRepository)
        clearInvocations(mObserver, mMovieRepository)
    }
}