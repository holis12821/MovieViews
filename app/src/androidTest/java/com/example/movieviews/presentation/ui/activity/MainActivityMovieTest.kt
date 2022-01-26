package com.example.movieviews.presentation.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.movieviews.R
import com.example.movieviews.external.dumydata.DataMovieDummy
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityMovieTest {

    private val dummyMovies = DataMovieDummy.getMovies()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMoviesAndTvShowTest() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

        onView(withId(R.id.navigation_tv_show))
            .perform(click())
        onView(withId(R.id.rv_tv_show))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

        onView(withId(R.id.navigation_movie))
            .perform(click())

    }

}