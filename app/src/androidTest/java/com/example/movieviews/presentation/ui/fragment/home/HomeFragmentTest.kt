package com.example.movieviews.presentation.ui.fragment.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.movieviews.R
import com.example.movieviews.external.dumydata.DataMovieDummy
import com.example.movieviews.presentation.ui.utils.FragmentTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    private val dummyMovies = DataMovieDummy.getMovies()
    private val dummyPopularMovie = dummyMovies.filter { it.isPopular }
    private val dummyFreeWatch = dummyMovies.filter { it.isFreeWatch }
    private val dummyTrendingMovie = dummyMovies.filter { it.isTrending }
    private val dummyUpComingMovie = dummyMovies.filter { it.isUpComing }
    private val textMovie = "The Movie DB"

    @get:Rule
    var fragmentTestRules = FragmentTestRule(HomeFragment::class.java)

    @Before
    fun setup() {
        fragmentTestRules.launchActivity(null)
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
        onView(withId(R.id.tv_movie_db))
            .check(matches(withText(textMovie)))
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
    fun testDetailPosterMovie() {
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
        onView(withId(R.id.iv_poster_image))
            .check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster_movie))
            .check(matches(isDisplayed()))
        //check title movie
        onView(withId(R.id.tv_title_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_movie))
            .check(matches(withText(dummyPopularMovie[0].title)))
        //check score film
        onView(withId(R.id.tv_score_film))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_score_film))
            .check(matches(withText(dummyPopularMovie[0].rating.toString())))
        //check movie certification
        onView(withId(R.id.tv_movie_certification))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_certification))
            .check(
                matches(
                    withText(
                        dummyPopularMovie[0].certification
                            .plus(
                                dummyPopularMovie[0].duration
                            )
                    )
                )
            )
        //check movie overview
        onView(withId(R.id.tv_desc_overview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc_overview))
            .check(matches(withText(dummyPopularMovie[0].overview)))
        //check tagline movie

    }



}