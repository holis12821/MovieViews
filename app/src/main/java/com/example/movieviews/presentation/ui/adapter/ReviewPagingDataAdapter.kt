package com.example.movieviews.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieviews.R
import com.example.movieviews.data.models.Review
import com.example.movieviews.databinding.ItemViewReviewBinding
import com.example.movieviews.external.constant.BASE_URL_IMAGE
import com.example.movieviews.external.extension.setImage
import com.example.movieviews.external.extension.setImageFromDrawable
import com.example.movieviews.external.utils.DateFormatter
import com.example.movieviews.external.utils.DiffUtils

class ReviewPagingDataAdapter :
    PagingDataAdapter<Review, ReviewPagingDataAdapter.ViewHolder>(DiffUtils.COMPARATOR_REVIEW) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { reviewResult ->
            holder.bindItem(reviewResult)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ItemViewReviewBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        ).also { itemViewReviewBinding ->
            return ViewHolder(itemViewReviewBinding)
        }
    }

    inner class ViewHolder(
        private val binding: ItemViewReviewBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        @SuppressLint("SetTextI18n")
        fun bindItem(review: Review) {
            val imageSize = itemView.context.getString(R.string.w500)
            val urlImage = "$BASE_URL_IMAGE$imageSize/${review.author_details?.avatar_path}"
            with(binding) {
                if (review.author_details?.avatar_path.isNullOrEmpty()) {
                    circleImageView.setImageFromDrawable(
                        ContextCompat.getDrawable(
                            itemView.context, R.drawable.place_holder_photo
                        )
                    )
                } else {
                    circleImageView.setImage(urlImage)
                }
                tvReviewBy.text = if (review.author_details?.name.isNullOrEmpty()) {
                    "-"
                } else {
                    itemView.context.getString(R.string.review_by_name, review.author_details?.name)
                }
                tvWritingDate.text =
                    itemView.context.getString(R.string.review_date, review.created_at?.let {
                        DateFormatter.getFormatted(
                            it
                        )
                    })
                ratingBar.rating = review.author_details?.rating?.toFloat() ?: 0F
                tvReview.text = review.content
            }
        }
    }
}