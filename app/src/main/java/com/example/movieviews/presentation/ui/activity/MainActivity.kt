package com.example.movieviews.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movieviews.R
import com.example.movieviews.core.BaseActivity
import com.example.movieviews.databinding.ActivityMainBinding
import com.example.movieviews.external.constant.KEY_MAIN_MENU
import com.example.movieviews.external.extension.showToast
import com.example.movieviews.presentation.ui.fragment.home.HomeFragment
import com.example.movieviews.presentation.ui.fragment.movie.MovieFragment
import com.example.movieviews.presentation.ui.fragment.tvshow.TvShowFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getResLayoutId(): Int = R.layout.activity_main

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        mBinding.navView.setOnItemSelectedListener { item ->
            onNavigationItemSelected(item.itemId)
            return@setOnItemSelectedListener true
        }
        setMainMenu(intent.getIntExtra(KEY_MAIN_MENU, 0))
    }

    private val homeMenu = listOf(
        R.id.navigation_home,
        R.id.navigation_movie,
        R.id.navigation_tv_show
    )
    private fun setMainMenu(menuId: Int) {
        if (menuId in homeMenu) {
            mBinding.navView.selectedItemId = menuId
        } else {
            onNavigationItemSelected(R.id.navigation_home)
        }
    }

    private var activeFragment: Fragment? = null

    private val homeFragment = HomeFragment()
    private val movieFragment = MovieFragment()
    private val tvShowFragment = TvShowFragment()

    private fun onNavigationItemSelected(menuId: Int) {
        var selectedFragment: Fragment? = null

        when (menuId) {
            R.id.navigation_home -> {
                if (homeFragment.isVisible) {
                    homeFragment.scrollToTop()
                    return
                }
                selectedFragment = homeFragment
            }
            R.id.navigation_movie -> {
                if (movieFragment.isVisible) {
                    return
                }
                selectedFragment = movieFragment
            }
            R.id.navigation_tv_show -> {
                if (tvShowFragment.isVisible) {
                    return
                }
                selectedFragment = tvShowFragment
            }
        }

        if (selectedFragment == null) return

        val ft = supportFragmentManager.beginTransaction()

        val current = activeFragment
        if (current != null && current.isAdded && current.isVisible) {
            ft.hide(current)
        }

        if (selectedFragment.isAdded) {
            ft.show(selectedFragment).commitNow()
        } else {
            try {
                val tag = getTag(selectedFragment)
                val oldFragment = supportFragmentManager.findFragmentByTag(tag)
                if (oldFragment != null) ft.remove(oldFragment)
                ft.add(R.id.nav_host_fragment, selectedFragment, tag).disallowAddToBackStack()
                    .commitNow()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        activeFragment = selectedFragment
    }

    private fun getTag(fragment: Fragment): String {
        return when (fragment) {
            is HomeFragment -> "home_fragment"
            is MovieFragment -> "movie_fragment"
            is TvShowFragment -> "tv_show_fragment"
            else -> "fragment"
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        intent?.let {
            val menuId = intent.getIntExtra(KEY_MAIN_MENU, 0)
            if (menuId != 0) {
                setMainMenu(menuId)
            } else {
                setMainMenu(R.id.navigation_home)
            }
        }
    }

    private var pressedTime: Long? = null
    override fun onBackPressed() {
        super.onBackPressed()
        val timeDelay = 2000
        if ((pressedTime?.plus(timeDelay) ?: 0) > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            showToast(this, getString(R.string.back_pressed_again))
        }
        pressedTime = System.currentTimeMillis()
    }
}