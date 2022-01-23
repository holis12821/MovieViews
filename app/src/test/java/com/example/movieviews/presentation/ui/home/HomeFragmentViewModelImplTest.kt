package com.example.movieviews.presentation.ui.home

import androidx.lifecycle.Observer
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.dumydata.DataMovieDummy
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeViewState
import com.example.movieviews.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeFragmentViewModelImplTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

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
            //example emit movieList to upstream flow
            mMovieRepository.getMovie(movieList = movieList)
                .onStart { HomeViewState.Progress(isLoading = true) }
                .catch {
                    HomeViewState.Progress(isLoading = false)
                    HomeViewState.ShowMessage("Error Occurred")
                }.collect { movieList ->
                    HomeViewState.Progress(isLoading = false)
                    assertNotNull(movieList)
                    assertEquals(42, movieList.size)
                }
        }
    }

    /**
     * Showing test when data success
     * */
    @Test
    fun `provide a movie list when successfully obtained from the repository on viewModel`() {
        testCoroutineRule.runBlockingTest {
            //given
            `when`(mMovieRepository.getMovie(movieList = movieList))
                .thenReturn(flowOf(movieList))

            //when
            mHomeFragmentViewModel.getMovie()

            //then
            verify(mMovieRepository).getMovie(movieList = movieList)
            verify(mObserver).onChanged(HomeViewState.ShowMovie(movieList))
            verifyNoMoreInteractions(mMovieRepository)
            clearInvocations(mObserver, mMovieRepository)
        }
    }

    @Test
    fun `Should fail when fetchFrom data to get error message`() {
        testCoroutineRule.runBlockingTest {
            //given
            `when`(mMovieRepository.getMovie(movieList = movieList))
                .thenReturn(emptyFlow())
            //when
            mHomeFragmentViewModel.getMovie()

            //then return
            verify(mMovieRepository, atLeastOnce()).getMovie(movieList)
            val viewState = HomeViewState.ShowMessage("Error occurred")
            assertEquals("Error occurred", viewState.message)

            verifyNoMoreInteractions(mMovieRepository)
            clearInvocations(mObserver, mMovieRepository)
        }
    }

    @After
    fun tearDown() {
        testCoroutineRule.onStop()
        clearInvocations(mMovieRepository)
    }
}
