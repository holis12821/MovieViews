package com.example.movieviews.presentation.ui.activity.splash_screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.example.movieviews.R
import com.example.movieviews.core.BaseActivity
import com.example.movieviews.databinding.ActivitySplashScreenBinding
import com.example.movieviews.presentation.ui.activity.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    override fun getResLayoutId(): Int = R.layout.activity_splash_screen

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        showSplashScreen()
    }

    private fun showSplashScreen() {
        object : Thread() {
            override fun run() {
                try {
                    sleep(3_000)
                    val intent = Intent(
                        this@SplashScreenActivity,
                        MainActivity::class.java
                    )
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }
}