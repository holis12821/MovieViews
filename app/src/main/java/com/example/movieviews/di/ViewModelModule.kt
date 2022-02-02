package com.example.movieviews.di

import com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel.DetailMovieActivityViewModelImpl
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.movie.viewmodel.MovieFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowFragmentViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeFragmentViewModelImpl(get()) }
    viewModel { MovieFragmentViewModelImpl(get()) }
    viewModel { TvShowFragmentViewModelImpl(get()) }
    viewModel { DetailMovieActivityViewModelImpl(get()) }
}