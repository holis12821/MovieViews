package com.example.movieviews.external.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.movieviews.data.models.MovieResult

class DiffUtilCallBack(
    private val mOldMovieResults: List<MovieResult>,
    private val mNewMovieResults: List<MovieResult>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = mOldMovieResults.size

    override fun getNewListSize(): Int = mNewMovieResults.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = mOldMovieResults[oldItemPosition].id == mNewMovieResults[newItemPosition].id


    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldMovie = mOldMovieResults[oldItemPosition]
        val newMovie = mNewMovieResults[newItemPosition]
        return oldMovie.name.equals(newMovie.name)
    }
}