package com.example.movieviews.presentation.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.movieviews.R
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
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5)
            )
    }

    @Test
    fun testLoadFreeToWatchMovie() {
        onView(withId(R.id.rv_top_rated_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_top_rated_movie))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4)
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
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1)
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
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(6)
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
                    5,
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
        //check score film
        onView(withId(R.id.tv_score_film))
            .check(matches(isDisplayed()))
        //check movie certification
        onView(withId(R.id.tv_movie_certification))
            .check(matches(isDisplayed()))
        //check movie genre
        onView(withId(R.id.tv_genre))
            .check(matches(isDisplayed()))
        //check movie overview
        onView(withId(R.id.tv_desc_overview))
            .check(matches(isDisplayed()))
        //check tagline movie
        onView(withId(R.id.tv_tagline))
            .check(matches(isDisplayed()))
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
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2)
            )
        onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    2,
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
        //check score film
        onView(withId(R.id.tv_score_film))
            .check(matches(isDisplayed()))
        //check movie certification
        onView(withId(R.id.tv_movie_certification))
            .check(matches(isDisplayed()))
        //check genre movie
        onView(withId(R.id.tv_genre))
            .check(matches(isDisplayed()))
        //check movie overview
        onView(withId(R.id.tv_desc_overview))
            .check(matches(isDisplayed()))
        //check tagline movie
        onView(withId(R.id.tv_tagline))
            .check(matches(isDisplayed()))
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
                    3
                )
            )
        onView(withId(R.id.rv_tv_show))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    3,
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
        //check score film
        onView(withId(R.id.tv_score_film))
            .check(matches(isDisplayed()))
        //check movie certification
        onView(withId(R.id.tv_movie_certification))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre))
            .check(matches(isDisplayed()))
        //check movie overview
        onView(withId(R.id.tv_desc_overview))
            .check(matches(isDisplayed()))
        //check tagline movie
        onView(withId(R.id.tv_tagline))
            .check(matches(isDisplayed()))
        //back to previous page
        Espresso.pressBack()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource)
    }
}