package com.example.movieviews.viewmodeltest

import androidx.lifecycle.Observer
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.dumydata.DataMovieDummy
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeViewState
import com.example.movieviews.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
class HomeFragmentViewModelImplTest {

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    private val mMovieRepository = mock<MovieRepository>()
    private val mHomeFragmentViewModel = HomeFragmentViewModelImpl(mMovieRepository)
    private val mObserver = mock<Observer<HomeViewState>>()

    private val movieList = DataMovieDummy.getMovies()


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        testCoroutineRule.onStart()
        mHomeFragmentViewModel.state.observeForever(mObserver)
    }

    /**
     * Test whether the data is successfully collected and also compare whether
     * the actual results of the data are not null and also in accordance
     * with the expectation of the amount of data.*/
    @Test
    fun getMovie() {
        testCoroutineRule.runBlockingTest {
            //example get flow and emit movieList to stream
            flow {
                emit(movieList)
            }.collect { movieList ->
                assertNotNull(movieList)
                assertEquals(42, movieList.size)
            }
        }
    }

    /**
     * Showing test when data success */

    @Test
    fun `provide a movie list when successfully obtained from the repository on viewModel`() {
        testCoroutineRule.runBlockingTest {
            `when`(mMovieRepository.getMovie())
                .thenReturn(flowOf(movieList))

            mHomeFragmentViewModel.getMovie()

            //verify when state on loading util onSuccess
            verify(mMovieRepository, atLeastOnce()).getMovie()
            verify(mObserver, atLeastOnce()).onChanged(HomeViewState.Progress(isLoading = true))
            verify(mObserver, atLeastOnce()).onChanged(HomeViewState.Progress(isLoading = false))
            verify(mObserver, atLeastOnce()).onChanged(HomeViewState.ShowMovie(movieList))
            verifyNoMoreInteractions(mMovieRepository)
            clearInvocations(mObserver, mMovieRepository)
        }
    }



    @After
    fun tearDown() {
        testCoroutineRule.onStop()
        verifyNoMoreInteractions(mMovieRepository)
        clearInvocations(mMovieRepository)
    }
}