package com.example.movieviews.presentation.ui.tvshow

import androidx.lifecycle.Observer
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.dumydata.DataMovieDummy
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowFragmentViewModelImpl
import com.example.movieviews.presentation.ui.utils.InstantRuleExecution
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(MockitoJUnitRunner::class)
class TvShowFragmentViewModelImplTest {
    //mock object
    private val mMovieRepository = mock<MovieRepository>()
    private val mTvShowFragmentViewModel = TvShowFragmentViewModelImpl(mMovieRepository)

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
        InstantRuleExecution.onStart()
        mTvShowFragmentViewModel.state.observeForever(mObserver)
    }

    @Test
    fun getTvShowMovie() {

        //given
        Mockito.`when`(mMovieRepository.getMovie())
            .thenReturn(movieList)


        //when
        mTvShowFragmentViewModel.getTvShowList()

        //then
        verify(mMovieRepository, times(1)).getMovie()
        verify(mObserver).onChanged(movieList)

        assertNotNull(movieList)
        assertEquals(42, movieList.size)
    }


    @After
    fun tearDown() {
        InstantRuleExecution.onStop()
        verifyNoMoreInteractions(mMovieRepository)
        clearInvocations(mObserver, mMovieRepository)
    }
}