package com.example.movieviews.presentation.ui.fragment.detail_screen.detail_movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movieviews.R
import com.example.movieviews.databinding.FragmentDetailMovieBinding

class DetailMovieFragment: Fragment() {

    private var mBinding: FragmentDetailMovieBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_detail_movie,
        container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {

    }


    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

}