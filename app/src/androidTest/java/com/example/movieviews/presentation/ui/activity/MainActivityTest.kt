package com.example.movieviews.presentation.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.movieviews.R
import com.example.movieviews.external.dumydata.DataMovieDummy
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private val dummyAllMovies = DataMovieDummy.getMovies()
    private val dummyPopularMovie = dummyAllMovies.filter { it.isPopular }
    private val dummyFreeWatch = dummyAllMovies.filter { it.isFreeWatch }
    private val dummyTrendingMovie = dummyAllMovies.filter { it.isTrending }
    private val dummyUpComingMovie = dummyAllMovies.filter { it.isUpComing }
    private val dummyCastMovie = dummyAllMovies.firstOrNull()?.cast
    private val dummyMovie = dummyAllMovies.filter { !it.isUpComing && !it.isTvSHow }
    private val dummyTvShow = dummyAllMovies.filter { it.isTvSHow }
    private val textMovie = "The Movie DB"

    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

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
        onView(withId(R.id.tv_movie_db))
            .check(matches(ViewMatchers.withText(textMovie)))
    }


    @Test
    fun testLoadPopularMovie() {
        onView(withId(R.id.rv_popular_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_popular_movie))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    dummyPopularMovie.size
                )
            )
    }

    @Test
    fun testLoadFreeToWatchMovie() {
        onView(withId(R.id.rv_free_watch))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_free_watch))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    dummyFreeWatch.size
                )
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
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    dummyTrendingMovie.size
                )
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
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    dummyUpComingMovie.size
                )
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
        //check poster movie
        onView(withId(R.id.iv_poster_movie))
            .check(matches(isDisplayed()))
        //check title movie
        onView(withId(R.id.tv_title_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_movie))
            .check(matches(ViewMatchers.withText(dummyPopularMovie[0].title)))
        //check score film
        onView(withId(R.id.tv_score_film))
            .check(matches(isDisplayed()))
        //check movie certification
        onView(withId(R.id.tv_movie_certification))
            .check(matches(isDisplayed()))
        //check movie overview
        onView(withId(R.id.tv_desc_overview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc_overview))
            .check(matches(ViewMatchers.withText(dummyPopularMovie[0].overview)))
        //check tagline movie
        onView(withId(R.id.tv_tagline))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_tagline))
            .check(matches(ViewMatchers.withText(dummyPopularMovie[0].tagLine)))
        //list cast movie
        onView(withId(R.id.rv_billed_cast))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_billed_cast))
            .perform(
                dummyCastMovie?.size?.let {
                    RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        it
                    )
                }
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
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            ))
        onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        //check poster movie
        onView(withId(R.id.iv_poster_movie))
            .check(matches(isDisplayed()))
        //check title movie
        onView(withId(R.id.tv_title_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_movie))
            .check(matches(ViewMatchers.withText(dummyMovie[0].title)))
        //check score film
        onView(withId(R.id.tv_score_film))
            .check(matches(isDisplayed()))
        //check movie certification
        onView(withId(R.id.tv_movie_certification))
            .check(matches(isDisplayed()))
        //check movie overview
        onView(withId(R.id.tv_desc_overview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc_overview))
            .check(matches(ViewMatchers.withText(dummyMovie[0].overview)))
        //check tagline movie
        onView(withId(R.id.tv_tagline))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_tagline))
            .check(matches(ViewMatchers.withText(dummyMovie[0].tagLine)))
        //list cast movie
        onView(withId(R.id.rv_billed_cast))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_billed_cast))
            .perform(
                dummyCastMovie?.size?.let {
                    RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        it
                    )
                }
            )
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
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            ))
        onView(withId(R.id.rv_tv_show))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    4,
                    click()
                )
            )
        //check detail tv show
        //check poster movie
        onView(withId(R.id.iv_poster_movie))
            .check(matches(isDisplayed()))
        //check title movie
        onView(withId(R.id.tv_title_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_movie))
            .check(matches(ViewMatchers.withText(dummyTvShow[4].title)))
        //check score film
        onView(withId(R.id.tv_score_film))
            .check(matches(isDisplayed()))
        //check movie certification
        onView(withId(R.id.tv_movie_certification))
            .check(matches(isDisplayed()))
        //check movie overview
        onView(withId(R.id.tv_desc_overview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc_overview))
            .check(matches(ViewMatchers.withText(dummyTvShow[4].overview)))
        //check tagline movie
        onView(withId(R.id.tv_tagline))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_tagline))
            .check(matches(ViewMatchers.withText(dummyTvShow[4].tagLine)))
        //back to previous page
        Espresso.pressBack()
    }
}