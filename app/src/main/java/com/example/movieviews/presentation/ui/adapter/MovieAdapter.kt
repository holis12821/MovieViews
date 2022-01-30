package com.example.movieviews.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.databinding.ItemViewMovieAdapterBinding
import com.example.movieviews.external.extension.convertDpToPixel
import com.example.movieviews.external.extension.setImage
import com.example.movieviews.external.utils.getScreenWidth

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    val list = mutableListOf<MovieEntity>()

    var maxWidth = 160
    var marginWidth = 12

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<MovieEntity>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun addMore(list: List<MovieEntity>) {
        this.list.addAll(list)
        notifyItemRangeInserted(this.list.size, list.size)
    }


    inner class ViewHolder(
        private val binding: ItemViewMovieAdapterBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bindItem(data: MovieEntity) {
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
            with(binding) {
                tvTitle.text = data.title
                ivPosterImage.setImage(data.posterUrl)
            }

            itemView.setOnClickListener {
                listener?.onItemClickCallback(data = data)
            }
        }
    }

    var listener: AdapterClickListener<MovieEntity>? = null

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