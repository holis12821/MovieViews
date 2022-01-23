package com.example.movieviews.presentation.ui.detail_screen

import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.dumydata.DataMovieDummy
import com.example.movieviews.presentation.ui.fragment.detail_screen.viewmodel.DetailMovieFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.detail_screen.viewmodel.DetailMovieViewState
import com.example.movieviews.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailMovieFragmentViewModelImplTest {

    //Test coroutine rule as rule test
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    //Mock object
    private val mMovieRepository = mock<MovieRepository>()
    private val mDetailMovieFragmentViewModel = DetailMovieFragmentViewModelImpl(mMovieRepository)

    private val mDummyMovieList = DataMovieDummy.getMovies()

    private val movieDummyId = 1
    private val movieTvShowId = 29

    /**
     * Things to prepare before the test takes place
     * */
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        testCoroutineRule.onStart()
        mDetailMovieFragmentViewModel.id = movieDummyId
    }

    @Test
    fun getDetailMovieById() {
        testCoroutineRule.runBlockingTest {
            mMovieRepository.getMovie(mDummyMovieList)
                .onStart { DetailMovieViewState.Progress(isLoading = true) }
                .catch {
                    DetailMovieViewState.Progress(isLoading = false)
                    DetailMovieViewState.ShowMessage("Error Occurred")
                }
                .collect { movieList ->
                    DetailMovieViewState.Progress(isLoading = false)
                    val movies = movieList.filter { !it.isUpComing && !it.isTvSHow }
                    val movie = movies.firstOrNull { it.id == mDetailMovieFragmentViewModel.id }
                    assertNotNull(movie)
                    assertEquals(mDummyMovieList[0].id, mDetailMovieFragmentViewModel.id)
                    assertEquals(mDummyMovieList[0].title, movie.title)
                    assertEquals(mDummyMovieList[0].overview, movie.overview)
                    assertEquals(mDummyMovieList[0].genres[0], movie.genres[0])
                    assertEquals(mDummyMovieList[0].releaseDate, movie.releaseDate)
                    assertEquals(mDummyMovieList[0].posterUrl, movie.posterUrl)
                }
        }
    }

    @Test
    fun getDetailTvShowById() {
        testCoroutineRule.runBlockingTest {
            mMovieRepository.getMovie(mDummyMovieList)
                .onStart { DetailMovieViewState.Progress(isLoading = true) }
                .catch {
                    DetailMovieViewState.Progress(isLoading = false)
                    DetailMovieViewState.ShowMessage("Error Occurred")
                }
                .collect { movieList ->
                    DetailMovieViewState.Progress(isLoading = false)
                    val tvShows = movieList.filter { it.isTvSHow }
                    mDetailMovieFragmentViewModel.id = movieTvShowId
                    val tvShow = tvShows.firstOrNull { it.id == mDetailMovieFragmentViewModel.id }
                    assertNotNull(tvShow)
                    assertEquals(mDummyMovieList[29].id, tvShow.id)
                    assertEquals(mDummyMovieList[29].title, tvShow.title)
                    assertEquals(mDummyMovieList[29].overview, tvShow.overview)
                    assertEquals(mDummyMovieList[29].genres[0], tvShow.genres[0])
                    assertEquals(mDummyMovieList[29].releaseDate, tvShow.releaseDate)
                    assertEquals(mDummyMovieList[29].posterUrl, tvShow.posterUrl)
                }
        }
    }

    @After
    fun tearDown() {
        testCoroutineRule.onStop()
        clearInvocations(mMovieRepository)
    }

}