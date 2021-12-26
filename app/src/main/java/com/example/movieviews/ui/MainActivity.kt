package com.example.movieviews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.movieviews.R
import com.example.movieviews.databinding.ActivityMainBinding
import com.example.movieviews.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var activityMainBinding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.btnCalculate.setOnClickListener(this@MainActivity)
        displayResult()
    }

    override fun onClick(v: View?) {
        val width = activityMainBinding.edtWidth.text.toString().trim()
        val height = activityMainBinding.edtHeight.text.toString().trim()
        val length = activityMainBinding.edtLength.text.toString().trim()

        when {
            width.isEmpty() -> {
                activityMainBinding.edtWidth.error = "Masih Kosong"
            }
            height.isEmpty() -> {
                activityMainBinding.edtHeight.error = "Masih Kosong"
            }
            length.isEmpty() -> {
                activityMainBinding.edtLength.error = "Masih Kosong"
            }
            else -> {
                viewModel.calculate(width, height, length)
                displayResult()
            }
        }
    }

    private fun displayResult() {
        activityMainBinding.tvResult.text = viewModel.result.toString()
    }
}