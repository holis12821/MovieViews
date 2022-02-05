package com.example.movieviews.presentation.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.movieviews.R
import com.example.movieviews.data.models.DetailMovieEntity
import com.example.movieviews.data.models.Genre
import com.example.movieviews.external.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    private val detailMovie = DetailMovieEntity(
        id = 271110,
        originalTitle = "Spider-Man: No Way Home",
        posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
        overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
        releaseDate = "2021-12-15",
        voteAverage = 8.4,
        genres = listOf(
            Genre(1, name = "Action"),
            Genre( 2 ,name = "Adventure"),
            Genre(3, name = "Science Fiction")
        ),
        tagline = "The Multiverse unleashed.",
        originalLanguage = "en"
    )

    private val detailTvShow = DetailMovieEntity(
        id = 99966,
        posterPath = "/ze4lhw0oLBHYmlM2KuZjBg0Sq6H.jpg",
        originalName = "지금 우리 학교는",
        overview = "A high school becomes ground zero for a zombie virus outbreak. Trapped students must fight their way out — or turn into one of the rabid infected.",
        voteAverage = 8.7,
        genres = listOf(
            Genre(1, name = "Drama"),
            Genre(3, name = "Sci-Fi & Fantasy")
        ),
        tagline = "Hope is the most painful torture for people who want to despair.",
        originalLanguage = "ko"
    )

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource)
    }

    @Test
    fun testDisplayingPosterMovie() {
        onView(withId(R.id.iv_poster_home))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testDisplayingLogoMovie() {
        onView(withId(R.id.iv_logo))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testDisplayingTextTitle() {
        onView(withId(R.id.tv_movie_db))
            .check(matches(isDisplayed()))
    }


    @Test
    fun testLoadPopularMovie() {
        onView(withId(R.id.rv_popular_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_popular_movie))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20)
            )
    }

    @Test
    fun testLoadFreeToWatchMovie() {
        onView(withId(R.id.rv_top_rated_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_top_rated_movie))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20)
            )
    }

    @Test
    fun testLoadTrendingMovie() {
        onView(withId(R.id.coordinator_layout))
            .perform(swipeUp())
        onView(withId(R.id.rv_trending_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_trending_movie))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20)
            )
    }

    @Test
    fun testLoadUpcomingMovie() {
        onView(withId(R.id.coordinator_layout))
            .perform(swipeUp())
        onView(withId(R.id.rv_upcoming_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_upcoming_movie))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20)
            )
    }

    @Test
    fun testDetailDetailPopularMovie() {
        onView(withId(R.id.coordinator_layout))
            .perform(swipeDown())
        onView(withId(R.id.rv_popular_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_popular_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        //check banner movie
        onView(withId(R.id.iv_poster_image))
            .check(matches(isDisplayed()))
        //check poster movie
        onView(withId(R.id.iv_poster_movie))
            .check(matches(isDisplayed()))
        //check title movie
        onView(withId(R.id.tv_title_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_movie))
            .check(matches(withText(detailMovie.originalTitle)))
        //check score film
        onView(withId(R.id.tv_score_film))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_score_film))
            .check(matches(withText("User Score : ${detailMovie.voteAverage.toString()}")))
        //check movie certification
        onView(withId(R.id.tv_movie_certification))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_certification))
            .check(matches(withText("${detailMovie.originalLanguage} * ${detailMovie.releaseDate}")))
        //check movie genre
        onView(withId(R.id.tv_genre))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre))
            .check(matches(withText("${detailMovie.genres?.get(0)?.name}, ${detailMovie.genres?.get(1)?.name}, ${detailMovie.genres?.get(2)?.name}")))
        //check movie overview
        onView(withId(R.id.tv_desc_overview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc_overview))
            .check(matches(withText(detailMovie.overview)))
        //check tagline movie
        onView(withId(R.id.tv_tagline))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_tagline))
            .check(matches(withText(detailMovie.tagline)))
        //list cast movie
        onView(withId(R.id.rv_billed_cast))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_billed_cast))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7)
            )
        Espresso.pressBack()
    }

    @Test
    fun testDetailMovie() {
        onView(withId(R.id.navigation_movie))
            .perform(click())
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20)
            )
        onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        //check banner movie
        onView(withId(R.id.iv_poster_image))
            .check(matches(isDisplayed()))
        //check poster movie
        onView(withId(R.id.iv_poster_movie))
            .check(matches(isDisplayed()))
        //check title movie
        onView(withId(R.id.tv_title_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_movie))
            .check(matches(withText(detailMovie.originalTitle)))
        //check score film
        onView(withId(R.id.tv_score_film))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_score_film))
            .check(matches(withText("User Score : ${detailMovie.voteAverage.toString()}")))
        //check movie certification
        onView(withId(R.id.tv_movie_certification))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_certification))
            .check(matches(withText("${detailMovie.originalLanguage} * ${detailMovie.releaseDate}")))
        //check genre movie
        onView(withId(R.id.tv_genre))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre))
            .check(matches(withText("${detailMovie.genres?.get(0)?.name}, ${detailMovie.genres?.get(1)?.name}, ${detailMovie.genres?.get(2)?.name}")))
        //check movie overview
        onView(withId(R.id.tv_desc_overview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc_overview))
            .check(matches(withText(detailMovie.overview)))
        //check tagline movie
        onView(withId(R.id.tv_tagline))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_tagline))
            .check(matches(withText(detailMovie.tagline)))
        //list cast movie
        onView(withId(R.id.rv_billed_cast))
            .check(matches(isDisplayed()))
        Espresso.pressBack()
    }

    @Test
    fun testDetailTvShowMovie() {
        //navigation to tvShow movie
        onView(withId(R.id.navigation_tv_show))
            .perform(click())
        //check rvTvShow
        onView(withId(R.id.rv_tv_show))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    20
                )
            )
        onView(withId(R.id.rv_tv_show))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        //check banner movie
        onView(withId(R.id.iv_poster_image))
            .check(matches(isDisplayed()))
        //check poster movie
        onView(withId(R.id.iv_poster_movie))
            .check(matches(isDisplayed()))
        //check title movie
        onView(withId(R.id.tv_title_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_movie))
            .check(matches(withText(detailTvShow.originalName)))
        //check score film
        onView(withId(R.id.tv_score_film))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_score_film))
            .check(matches(withText("User Score : ${detailTvShow.voteAverage.toString()}")))
        //check movie certification
        onView(withId(R.id.tv_movie_certification))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_certification))
            .check(matches(withText("${detailTvShow.originalLanguage} * -")))
        onView(withId(R.id.tv_genre))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre))
            .check(matches(withText("${detailTvShow.genres?.get(0)?.name}, ${detailTvShow.genres?.get(1)?.name}")))
        //check movie overview
        onView(withId(R.id.tv_desc_overview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc_overview))
            .check(matches(withText(detailTvShow.overview)))
        //check tagline movie
        onView(withId(R.id.tv_tagline))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_tagline))
            .check(matches(withText(detailTvShow.tagline)))
        //back to previous page
        Espresso.pressBack()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource)
    }
}