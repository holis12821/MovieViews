package com.example.movieviews.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieviews.R
import com.example.movieviews.data.models.TvShowEntity
import com.example.movieviews.databinding.ItemViewTvShowAdapterBinding
import com.example.movieviews.external.extension.convertDpToPixel
import com.example.movieviews.external.utils.getScreenWidth
import com.example.movieviews.external.utils.setImage

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {
    val list = mutableListOf<TvShowEntity>()

    var maxWidth = 160
    var marginWidth = 12

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<TvShowEntity>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun addMore(list: List<TvShowEntity>) {
        this.list.addAll(list)
        notifyItemRangeInserted(this.list.size, list.size)
    }

    inner class ViewHolder(
       private val binding: ItemViewTvShowAdapterBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bindItem(data: TvShowEntity) {
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
                        ))
            }
            binding.data = data
            binding.tvTitle.text = data.title
            binding.ivPoster.setImage(data.posterUrl)
        }
    }

    var listener: AdapterClickListener<TvShowEntity>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_view_tv_show_adapter,
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