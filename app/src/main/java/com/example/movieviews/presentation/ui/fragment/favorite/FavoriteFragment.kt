package com.example.movieviews.presentation.ui.fragment.favorite

import com.example.movieviews.R
import com.example.movieviews.core.BaseFragment
import com.example.movieviews.databinding.FragmentFavoriteBinding
import com.example.movieviews.external.constant.TAB_TITLES_FRAGMENT
import com.example.movieviews.presentation.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {
    override fun getResLayoutId(): Int = R.layout.fragment_favorite

    override fun onViewCreated() {
        setupAdapterViewPager()
    }

    private fun setupAdapterViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(requireActivity())
        mBinding?.apply {
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tabLayout, position ->
                tabLayout.text = getString(TAB_TITLES_FRAGMENT[position])
            }.attach()
        }
    }
}