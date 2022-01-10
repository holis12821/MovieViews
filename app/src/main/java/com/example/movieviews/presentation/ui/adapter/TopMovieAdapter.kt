package com.example.movieviews.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieviews.R
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.databinding.ItemViewTopMovieAdapterBinding

class TopMovieAdapter : RecyclerView.Adapter<TopMovieAdapter.ViewHolder>() {
    private val list = mutableListOf<MovieEntity>()

    inner class ViewHolder(
        binding: ItemViewTopMovieAdapterBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bindItem(data: MovieEntity) {

        }
    }

    var listener: AdapterClickListener<MovieEntity>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_view_top_movie_adapter,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int = list.size
}