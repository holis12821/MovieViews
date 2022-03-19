package com.example.movieviews.presentation.ui.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movieviews.R
import com.example.movieviews.core.BaseActivity
import com.example.movieviews.databinding.ActivityMainBinding
import com.example.movieviews.external.extension.gone
import com.example.movieviews.external.extension.visible

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var mNavController: NavController

    override fun getResLayoutId(): Int = R.layout.activity_main

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        //set NavHostFragment to find fragment based on id
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        mNavController = navHostFragment.navController
        //passing each menu ID as a set of Ids, because each
        //menu should be considered as top level destination
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_movie,
                R.id.navigation_tv_show,
                R.id.navigation_favorite
            )
        )
        setupActionBarWithNavController(mNavController, appBarConfiguration)
        mBinding.navView.setupWithNavController(mNavController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(mNavController, null)
    }
}