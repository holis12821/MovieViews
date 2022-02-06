package com.example.movieviews.presentation.ui.detail_screen

import androidx.lifecycle.Observer
import com.example.movieviews.data.models.DetailMovieEntity
import com.example.movieviews.data.models.Genre
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel.DetailMovieActivityViewModelImpl
import com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel.DetailMovieViewState
import com.example.movieviews.utils.TestCoroutineRule
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailMovieActivityViewModelImplTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val mMovieRepository = mock<MovieRepository>()
    private val mDetailActivityMovieViewModel = DetailMovieActivityViewModelImpl(mMovieRepository)
    private val mObserver = mock<Observer<DetailMovieViewState>>()

    private val detailMovie = DetailMovieEntity(
        id = 271110,
        originalTitle = "Spider-Man: No Way Home",
        posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
        overview = """Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. 
            |When he asks for help from Doctor Strange the stakes become even more dangerous,
            | forcing him to discover what it truly means to be Spider-Man.""".trimMargin(),
        releaseDate = "2021-12-15",
        voteAverage = 8.4,
        genres = listOf(
            Genre(1, name = "Action"),
            Genre(2, name = "Adventure"),
            Genre(3, name = "Science Fiction")
        ),
        originalLanguage = "en"
    )

    private val detailTvShow = DetailMovieEntity(
        id = 135753,
        posterPath = "/5bTF522eYn6g6r7aYqFpTZzmQq6.jpg",
        originalTitle = "사랑의 꽈배기",
        overview = """A drama depicting a sweet twist in love between the parents and children of three families around
            | the love of two main characters.""".trimMargin(),
        voteAverage = 4.5,
        genres = listOf(
            Genre(1, name = "Family"),
            Genre(2, name = "Comedy"),
            Genre(3, name = "Drama")
        ),
        originalLanguage = "ko"
    )

    @Captor
    private lateinit var captor: ArgumentCaptor<DetailMovieViewState>


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        testCoroutineRule.onStart()
        mDetailActivityMovieViewModel.state.observeForever(mObserver)
    }

    /**
     * Showing test when data success
     * */
    @Test
    fun `Check when successfully movie detail obtained from the repository on viewModel`() {
        testCoroutineRule.runBlockingTest {
            mDetailActivityMovieViewModel.getDetailMovie()
            captor.run {
                `when`(mMovieRepository.getDetailMovie(movie_id = detailMovie.id ?: 0, API_KEY))
                    .thenReturn(flowOf(detailMovie))
                val detailMovieEntity =
                    mMovieRepository.getDetailMovie(detailMovie.id ?: 0, API_KEY)
                verify(mMovieRepository, atLeastOnce()).getDetailMovie(detailMovie.id ?: 0, API_KEY)
                verify(mObserver, Mockito.times(3)).onChanged(capture())
                Truth.assertThat(allValues[0] is DetailMovieViewState.Init)
                Truth.assertThat(allValues[1] is DetailMovieViewState.Loading)
                Truth.assertThat(allValues[2] is DetailMovieViewState.ShowDetailMovie)
                assertNotNull(detailMovieEntity.firstOrNull())
                assertEquals(detailMovie.id, detailMovieEntity.firstOrNull()?.id)
                assertEquals(
                    detailMovie.originalTitle,
                    detailMovieEntity.firstOrNull()?.originalTitle
                )
                assertEquals(detailMovie.voteAverage, detailMovieEntity.firstOrNull()?.voteAverage)
                assertEquals(detailMovie.releaseDate, detailMovieEntity.firstOrNull()?.releaseDate)
                assertEquals(detailMovie.genres, detailMovieEntity.firstOrNull()?.genres)
                assertEquals(detailMovie.tagline, detailMovieEntity.firstOrNull()?.tagline)
                assertEquals(detailMovie.overview, detailMovieEntity.firstOrNull()?.overview)
                clearInvocations(mObserver)
            }
        }
    }

    @Test
    fun `Check when successfully tv show detail obtained from the repository on viewModel`() {
        testCoroutineRule.runBlockingTest {
            mDetailActivityMovieViewModel.getDetailTvShow()
            captor.run {
                `when`(mMovieRepository.getDetailTvShow(detailTvShow.id ?: 0, API_KEY))
                    .thenReturn(flowOf(detailTvShow))
                val detailTvShowEntity =
                    mMovieRepository.getDetailTvShow(detailTvShow.id ?: 0, API_KEY)
                verify(mMovieRepository, atLeastOnce()).getDetailTvShow(
                    detailTvShow.id ?: 0,
                    API_KEY
                )
                verify(mObserver, Mockito.times(3)).onChanged(capture())
                Truth.assertThat(allValues[0] is DetailMovieViewState.Init)
                Truth.assertThat(allValues[1] is DetailMovieViewState.Loading)
                Truth.assertThat(allValues[2] is DetailMovieViewState.ShowDetailMovie)
                assertNotNull(detailTvShowEntity.firstOrNull())
                assertEquals(detailTvShow.id, detailTvShowEntity.firstOrNull()?.id)
                assertEquals(
                    detailTvShow.originalName,
                    detailTvShowEntity.firstOrNull()?.originalName
                )
                assertEquals(
                    detailTvShow.voteAverage,
                    detailTvShowEntity.firstOrNull()?.voteAverage
                )
                assertEquals(
                    detailTvShow.releaseDate,
                    detailTvShowEntity.firstOrNull()?.releaseDate
                )
                assertEquals(detailTvShow.genres, detailTvShowEntity.firstOrNull()?.genres)
                assertEquals(detailTvShow.tagline, detailTvShowEntity.firstOrNull()?.tagline)
                assertEquals(detailTvShow.overview, detailTvShowEntity.firstOrNull()?.overview)
                clearInvocations(mObserver)
            }
        }
    }

    @Test
    fun `Should fail when fetchFrom data detail movie to get error message`() {
        testCoroutineRule.runBlockingTest {
            mDetailActivityMovieViewModel.getDetailMovie()
            captor.run {
                Mockito.verify(mObserver, Mockito.times(3)).onChanged(capture())
                Truth.assertThat(allValues[0] is DetailMovieViewState.Init)
                //check if the message you got will generate an error message
                Truth.assertThat(allValues[1] is DetailMovieViewState.Message)
                clearInvocations(mObserver)
            }
        }
    }

    @Test
    fun `Should fail when fetchFrom data detail tv show to get error message`() {
        testCoroutineRule.runBlockingTest {
            mDetailActivityMovieViewModel.getDetailTvShow()
            captor.run {
                Mockito.verify(mObserver, Mockito.times(3)).onChanged(capture())
                Truth.assertThat(allValues[0] is DetailMovieViewState.Init)
                //check if the message you got will generate an error message
                Truth.assertThat(allValues[1] is DetailMovieViewState.Message)
                clearInvocations(mObserver)
            }
        }
    }

    @After
    fun tearDown() {
        testCoroutineRule.onStop()
        clearInvocations(mMovieRepository)
    }
}