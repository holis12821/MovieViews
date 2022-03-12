package com.example.movieviews.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.movieviews.R
import com.example.movieviews.databinding.ItemViewLoadStateBinding

class LoadingStateViewHolder(
    private val binding: ItemViewLoadStateBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        /**
         * When the retry button is clicked it will generate
         * a callback, which will trigger the button to perform or display
         * the event.*/
        binding.retryBtn.setOnClickListener { retry.invoke() }
    }

    fun bindLoad(loadState: LoadState) {
        if (loadState is LoadState.Error) binding.errorMsg.text =
            loadState.error.localizedMessage
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryBtn.isVisible = loadState !is LoadState.Loading
        binding.errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(
            parent: ViewGroup,
            retry: () -> Unit
        ): LoadingStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_load_state, parent, false)
            val binding = ItemViewLoadStateBinding.bind(view)
            return LoadingStateViewHolder(binding, retry)
        }
    }
}