package com.example.movieviews.presentation.ui.tvshow

import androidx.lifecycle.Observer
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.dumydata.DataMovieDummy
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeViewState
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowViewState
import com.example.movieviews.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvShowFragmentViewModelImplTest {

    //test coroutine rule as rule test
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    //mock object
    private val mMovieRepository = mock<MovieRepository>()
    private val mTvShowFragmentViewModel = TvShowFragmentViewModelImpl(mMovieRepository)

    private val mObserver = mock<Observer<TvShowViewState>>()

    private val movieList = DataMovieDummy.getMovies()

    /**
     * Things to prepare before the test takes place
     * */
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        testCoroutineRule.onStart()
        mTvShowFragmentViewModel.state.observeForever(mObserver)
    }

    /**
     * Test whether the data is successfully collected and also compare whether
     * the actual results of the data are not null and also in accordance
     * with the expectation of the amount of data.
     * */

    @Test
    fun getTvShowList() {
        testCoroutineRule.runBlockingTest {
            /**
             * Example emit movieList to upstream flow.
             * test whether data has been success emit to upstream and
             * data has been collected from upstream to downstream
             * */

            /**
             * The concept is line a flow on a river
             * */
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

    //showing test data success
    @Test
    fun `Provide a movie list when list data success`() {
        testCoroutineRule.runBlockingTest {
            //given
            `when`(mMovieRepository.getMovie(movieList = movieList))
                .thenReturn(flowOf(movieList))

            //when
            mTvShowFragmentViewModel.getTvShowList()

            //then
            verify(mMovieRepository).getMovie(movieList = movieList)
            verify(mObserver).onChanged(TvShowViewState.ShowTvShow(movieList))

            verifyNoMoreInteractions(mMovieRepository)
            clearInvocations(mObserver, mMovieRepository)
        }
    }

    @Test
    fun `Given message error when fetch data and should be get error message`() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Occurred"
            //given
            `when`(mMovieRepository.getMovie(movieList = movieList))
                .thenReturn(emptyFlow())

            //when
            mTvShowFragmentViewModel.getTvShowList()

            //then
            verify(mMovieRepository).getMovie(movieList = movieList)
            val viewState = TvShowViewState.ShowMessage("Error Occurred")
            assertEquals(errorMessage, viewState.message)
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