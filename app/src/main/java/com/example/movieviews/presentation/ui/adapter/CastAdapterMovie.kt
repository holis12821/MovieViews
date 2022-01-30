package com.example.movieviews.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieviews.data.models.CastEntity
import com.example.movieviews.databinding.ItemViewCastAdapterBinding
import com.example.movieviews.external.extension.convertDpToPixel
import com.example.movieviews.external.extension.setImage
import com.example.movieviews.external.utils.getScreenWidth

class CastAdapterMovie : RecyclerView.Adapter<CastAdapterMovie.ViewHolder>() {
    val list = mutableListOf<CastEntity>()

    var maxWidth = 160
    var marginWidth = 12

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<CastEntity>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemViewCastAdapterBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bindItem(data: CastEntity) {
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
                ivPosterCast.setImage(data.posterUrl)
                tvOriginalName.text = data.originalName
                tvNameCast.text = data.nameCast
            }
        }
    }

    var listener: AdapterClickListener<CastEntity>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ItemViewCastAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        ).also { itemViewCastAdapterBinding ->
            return ViewHolder(itemViewCastAdapterBinding)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int = list.size
}
