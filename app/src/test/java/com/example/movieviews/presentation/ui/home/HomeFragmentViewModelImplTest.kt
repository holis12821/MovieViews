package com.example.movieviews.presentation.ui.home

import androidx.lifecycle.Observer
import com.example.movieviews.data.local.MovieEntity
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.dumydata.DataMovieDummy
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeFragmentViewModelImpl
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*


@RunWith(MockitoJUnitRunner::class)
class HomeFragmentViewModelImplTest {

    //mock object
    private val mMovieRepository = mock<MovieRepository>()
    private val mHomeFragmentViewModel = HomeFragmentViewModelImpl(mMovieRepository)

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
