package com.example.movieviews.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieviews.R
import com.example.movieviews.data.local.MovieEntity
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.databinding.ItemViewMovieAdapterBinding
import com.example.movieviews.external.constant.BASE_URL_IMAGE
import com.example.movieviews.external.extension.convertDpToPixel
import com.example.movieviews.external.extension.setImage
import com.example.movieviews.external.utils.getScreenWidth

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    val list = mutableListOf<MovieResult>()

    var maxWidth = 160
    var marginWidth = 12

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<MovieResult>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun addMore(list: List<MovieResult>) {
        this.list.addAll(list)
        notifyItemRangeInserted(this.list.size, list.size)
    }


    inner class ViewHolder(
        private val binding: ItemViewMovieAdapterBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bindItem(data: MovieResult) {
            if (maxWidth > 0) {
                binding.layoutContent.layoutParams.width =
                    convertDpToPixel(itemView.context, maxWidth)
                binding.wrapperImage.layoutParams.height =
                    convertDpToPixel(itemView.context, maxWidth)
            } else {
                binding.layoutContent.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                binding.wrapperImage.layoutParams.height =
                    (getScreenWidth().div(2)).minus(
                        convertDpToPixel(
                            itemView.context,
                            marginWidth
                        )
                    )

            }
            val imageSize = itemView.context.getString(R.string.w500)
            val urlImage = "$BASE_URL_IMAGE$imageSize/${data.posterPath}"
            with(binding) {
                if (!data.originalTitle.isNullOrEmpty()) {
                    tvTitle.text = data.originalTitle
                } else {
                    tvTitle.text = data.originalName
                }
                ivPosterImage.setImage(urlImage)
            }

            itemView.setOnClickListener {
                listener?.onItemClickCallback(data = data)
            }
        }
    }

    var listener: AdapterClickListener<MovieResult>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ItemViewMovieAdapterBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        ).also { itemViewMovieBinding ->
            return ViewHolder(itemViewMovieBinding)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int = list.size
}