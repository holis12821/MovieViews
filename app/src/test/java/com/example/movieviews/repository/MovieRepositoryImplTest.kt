package com.example.movieviews.repository

import com.example.movieviews.data.models.DetailMovieEntity
import com.example.movieviews.data.models.Genre
import com.example.movieviews.data.remote.RemoteDataSource
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.external.constant.language
import com.example.movieviews.external.dumydata.DataMovieDummy
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
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val service = mock<RemoteDataSource>()


    private val response = DataMovieDummy.getMovies()
    private val tvShowResponse = DataMovieDummy.getTvShow()
    private val errorMessage = "Error to get data movie"

    private val detailMovie = DetailMovieEntity(
        id = 634649,
        originalTitle = "Spider-Man: No Way Home",
        posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
        overview = """Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero.
            | When he asks for help from Doctor Strange the stakes become even more dangerous, 
            |forcing him to discover what it truly means to be Spider-Man.""".trimMargin(),
        releaseDate = "2021-12-15",
        voteAverage = 8.4,
        genres = listOf(
            Genre(1, name = "Action"),
            Genre(2, name = "Adventure"),
            Genre(3, name = "Science Fiction")
        ),
        status_message = errorMessage,
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
        status_message = errorMessage,
        originalLanguage = "ko"
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
            val baseResponse = service.getPopularMovie(API_KEY, language)
            verify(service, atLeastOnce()).getPopularMovie(API_KEY, language)
            assertNotNull(baseResponse)
            assertEquals(10, baseResponse.results?.size)
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
            val baseResponse = service.getDiscoverMovie(API_KEY, language)
            verify(service, atLeastOnce()).getDiscoverMovie(API_KEY, language)
            assertNotNull(baseResponse)
            assertEquals(10, baseResponse.results?.size)
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
            val baseResponse = service.getDiscoverTvShow(API_KEY, language)
            verify(service, atLeastOnce()).getDiscoverTvShow(API_KEY, language)
            assertNotNull(baseResponse)
            assertEquals(10, baseResponse.results?.size)
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
            val detailMovieEntity = service.getDetailMovie(movie_id = detailMovie.id ?: 0, API_KEY)
            verify(service, atLeastOnce()).getDetailMovie(movie_id = detailMovie.id ?: 0, API_KEY)
            assertNotNull(detailMovieEntity)
            assertEquals(
                detailMovie.id,
                detailMovieEntity.id
            )
            assertEquals(
                detailMovie.originalTitle,
                detailMovieEntity.originalTitle
            )
            assertEquals(
                detailMovie.voteAverage,
                detailMovieEntity.voteAverage
            )
            assertEquals(
                detailMovie.releaseDate,
                detailMovieEntity.releaseDate
            )
            assertEquals(
                detailMovie.genres,
                detailMovieEntity.genres
            )
            assertEquals(
                detailMovie.tagline,
                detailMovieEntity.tagline
            )

            assertEquals(
                detailMovie.overview,
                detailMovieEntity.overview
            )
        }
    }


    @Test
    fun `Given repository when get detail tv show form API should return success`() {
        testCoroutineRule.runBlockingTest {
            `when`(service.getDetailTvShow(tv_id = detailTvShow.id ?: 0, API_KEY))
                .thenReturn(detailTvShow)
            val detailTvShowEntity = service.getDetailTvShow(tv_id = detailTvShow.id ?: 0, API_KEY)
            verify(service, atLeastOnce()).getDetailTvShow(tv_id = detailTvShow.id ?: 0, API_KEY)
            assertNotNull(detailTvShowEntity)
            assertEquals(
                detailTvShow.id,
                detailTvShowEntity.id
            )
            assertEquals(
                detailTvShow.originalName,
                detailTvShowEntity.originalName
            )
            assertEquals(
                detailTvShow.voteAverage,
                detailTvShowEntity.voteAverage
            )
            assertEquals(
                detailTvShow.releaseDate,
                detailTvShowEntity.releaseDate
            )
            assertEquals(
                detailTvShow.genres,
                detailTvShowEntity.genres
            )
            assertEquals(
                detailTvShow.tagline,
                detailTvShowEntity.tagline
            )

            assertEquals(
                detailTvShow.overview,
                detailTvShowEntity.overview
            )
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