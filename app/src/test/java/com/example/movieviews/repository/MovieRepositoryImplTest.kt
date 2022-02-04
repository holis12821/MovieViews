package com.example.movieviews.repository

import com.example.movieviews.data.models.BaseResponse
import com.example.movieviews.data.models.DetailMovieEntity
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.remote.RemoteDataSource
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.external.constant.language
import com.example.movieviews.utils.TestCoroutineRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val service = mock<RemoteDataSource>()

    private val errorMessage = "Error to get data movie"

    private val response = BaseResponse<List<MovieResult>>(
        results = mutableListOf(
            MovieResult(
                id = 634649,
                posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                originalTitle = "Spider-Man: No Way Home",
                overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                releaseDate = "2021-12-15",
                voteAverage = 8.4,
                voteCount = 6941,
                adult = false,
                backdropPath = "/iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg"
            )
        ),
        status_message = errorMessage
    )

    private val tvShowResponse = BaseResponse<List<MovieResult>>(
        results = mutableListOf(
            MovieResult(
                id = 634649,
                posterPath = "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                originalTitle = "Euphoria",
                overview = "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
                popularity = 3549.827,
                voteAverage = 8.4,
                voteCount = 6102,
                adult = false,
                backdropPath = "/oKt4J3TFjWirVwBqoHyIvv5IImd.jpg"
            )
        ),
        status_message = errorMessage
    )

    private val detailMovie = DetailMovieEntity(
        id = 271110,
        originalTitle = "Captain America: Civil War",
        posterPath = "/rAGiXaUfPzY7CDEyNKUofk3Kw2e.jpg",
        overview = "Following the events of Age of Ultron, the collective governments of the world pass an act designed to regulate all superhuman activity. This polarizes opinion amongst the Avengers, causing two factions to side with Iron Man or Captain America, which causes an epic battle between former allies",
        popularity = 185.97,
        adult = false,
        status_message = errorMessage
    )

    private val detailTvShow = DetailMovieEntity(
        id = 271110,
        originalTitle = "The Flash",
        posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
        overview = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
        popularity = 954.991,
        adult = false,
        status_message = errorMessage
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        testCoroutineRule.onStart()
    }

    @Test
    fun `Given repository when get movie popular from API should return success`() {
        testCoroutineRule.runBlockingTest {
            `when`(service.getPopularMovie(API_KEY, language))
                .thenReturn(response)
            service.getPopularMovie(API_KEY, language)
            verify(service, atLeastOnce()).getPopularMovie(API_KEY, language)
        }
    }

    @Test
    fun `Given repository when get movie popular from API should return error`() {
        testCoroutineRule.runBlockingTest {
            val error = Throwable(response.status_message)
            `when`(service.getPopularMovie(API_KEY, language))
                .thenReturn(response)
            service.getPopularMovie(API_KEY, language)
            verify(service, atLeastOnce()).getPopularMovie(API_KEY, language)
            assertEquals(errorMessage, error.message)
        }
    }

    @Test
    fun `Given repository when get discover movie from API should return success`() {
        testCoroutineRule.runBlockingTest {
            `when`(service.getDiscoverMovie(API_KEY, language))
                .thenReturn(response)
            service.getDiscoverMovie(API_KEY, language)
            verify(service, atLeastOnce()).getDiscoverMovie(API_KEY, language)
        }
    }

    @Test
    fun `Given repository when get discover movie from API should return error`() {
        testCoroutineRule.runBlockingTest {
            val error = Throwable(response.status_message)
            `when`(service.getDiscoverMovie(API_KEY, language))
                .thenReturn(response)
            service.getDiscoverMovie(API_KEY, language)
            verify(service, atLeastOnce()).getDiscoverMovie(API_KEY, language)
            assertEquals(errorMessage, error.message)
        }
    }

    @Test
    fun `Given repository when get discover tv show from API should return success`() {
        testCoroutineRule.runBlockingTest {
            `when`(service.getDiscoverTvShow(API_KEY, language))
                .thenReturn(tvShowResponse)
            service.getDiscoverTvShow(API_KEY, language)
            verify(service, atLeastOnce()).getDiscoverTvShow(API_KEY, language)
        }
    }

    @Test
    fun `Given repository when get discover tv show from API should return error`() {
        testCoroutineRule.runBlockingTest {
            val error = Throwable(response.status_message)
            `when`(service.getDiscoverTvShow(API_KEY, language))
                .thenReturn(tvShowResponse)
            service.getDiscoverMovie(API_KEY, language)
            verify(service, atLeastOnce()).getDiscoverMovie(API_KEY, language)
            assertEquals(errorMessage, error.message)
        }
    }


    @Test
    fun `Given repository when get detail movie form API should return success`() {
        testCoroutineRule.runBlockingTest {
            `when`(service.getDetailMovie(movie_id = detailMovie.id ?: 0, API_KEY))
                .thenReturn(detailMovie)
            service.getDetailMovie(movie_id = detailMovie.id ?: 0, API_KEY)
            verify(service, atLeastOnce()).getDetailMovie(movie_id = detailMovie.id ?: 0, API_KEY)
        }
    }

    @Test
    fun `Given repository when get detail tv show from API should return error`() {
        testCoroutineRule.runBlockingTest {
            val error = Throwable(response.status_message)
            `when`(service.getDetailTvShow(detailTvShow.id ?: 0, API_KEY))
                .thenReturn(detailTvShow)
            service.getDetailTvShow(detailTvShow.id ?: 0, API_KEY)
            verify(service, atLeastOnce()).getDetailTvShow(detailTvShow.id ?: 0, API_KEY)
            assertEquals(errorMessage, error.message)
        }
    }


    @After
    fun tearDown() {
        testCoroutineRule.onStop()
        clearInvocations(service)
    }
}