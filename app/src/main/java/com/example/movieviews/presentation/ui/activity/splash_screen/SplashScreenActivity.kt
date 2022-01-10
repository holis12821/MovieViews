package com.example.movieviews.presentation.ui.activity.splash_screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.movieviews.R
import com.example.movieviews.databinding.ActivitySplashScreenBinding
import com.example.movieviews.presentation.ui.activity.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    private var mBinding: ActivitySplashScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        mBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_splash_screen
        )
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