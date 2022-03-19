package com.example.movieviews.presentation.ui.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieviews.external.constant.TAB_TITLES_FRAGMENT
import com.example.movieviews.presentation.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment: Fragment() { /*
    private var mBinding: FragmentFavoriteBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFavoriteBinding.inflate(
            inflater,
            container,
            false
        )
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setupAdapterViewPager()
    }

    private fun setupAdapterViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(requireActivity())
        mBinding?.apply {
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout, viewPager) {tabLayout, position ->
                tabLayout.text = getString(TAB_TITLES_FRAGMENT[position])
            }.attach()
        }
    }*/
}