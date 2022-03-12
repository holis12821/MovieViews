package com.example.movieviews.presentation.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieviews.external.constant.TAB_TITLES_FRAGMENT
import com.example.movieviews.presentation.ui.fragment.favorite.movie.FavoriteMovieFragment
import com.example.movieviews.presentation.ui.fragment.favorite.tvshow.FavoriteTvShowFragment

class ViewPagerAdapter(mFragmentActivity: FragmentActivity) :
    FragmentStateAdapter(mFragmentActivity) {
    override fun getItemCount(): Int {
        return TAB_TITLES_FRAGMENT.size
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FavoriteMovieFragment()
            1 -> fragment = FavoriteTvShowFragment()
        }
        return fragment as Fragment
    }

}