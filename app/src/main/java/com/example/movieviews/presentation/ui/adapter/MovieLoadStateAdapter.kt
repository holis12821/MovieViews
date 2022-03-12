package com.example.movieviews.presentation.ui.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class MovieLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadingStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bindLoad(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder =
        LoadingStateViewHolder.create(parent, retry)
}