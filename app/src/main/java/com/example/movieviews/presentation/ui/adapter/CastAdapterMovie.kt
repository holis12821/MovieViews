package com.example.movieviews.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieviews.R
import com.example.movieviews.data.models.Cast
import com.example.movieviews.databinding.ItemViewCastAdapterBinding
import com.example.movieviews.external.constant.BASE_URL_IMAGE
import com.example.movieviews.external.extension.convertDpToPixel
import com.example.movieviews.external.extension.setImage
import com.example.movieviews.external.extension.setImageFromDrawable
import com.example.movieviews.external.utils.getScreenWidth

class CastAdapterMovie : RecyclerView.Adapter<CastAdapterMovie.ViewHolder>() {
    val list = mutableListOf<Cast>()

    var maxWidth = 160
    var marginWidth = 12

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Cast>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemViewCastAdapterBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bindItem(data: Cast) {
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

            itemView.setOnClickListener {
                listener?.onItemClickCallback(data = data)
            }
            val imageSize = itemView.context.getString(R.string.w500)
            val urlImage = "$BASE_URL_IMAGE$imageSize/${data.profilePath}"
            with(binding) {
                if (data.profilePath.isNullOrEmpty()) {
                    ivPosterCast.setImageFromDrawable(
                        ContextCompat.getDrawable(itemView.context,
                        R.drawable.place_holder_photo)
                    )
                } else {
                    ivPosterCast.setImage(urlImage)
                }
                tvOriginalName.text = data.originalName
                tvNameCast.text = data.character
            }
        }
    }

    var listener: AdapterClickListener<Cast>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_view_cast_adapter, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int = list.size
}
