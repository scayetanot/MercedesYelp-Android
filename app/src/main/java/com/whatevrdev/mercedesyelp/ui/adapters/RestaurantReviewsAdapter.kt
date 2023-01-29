package com.whatevrdev.mercedesyelp.ui.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.whatevrdev.domain.entities.YelpReview
import com.whatevrdev.mercedesyelp.ui.viewholders.ReviewViewHolder

class RestaurantReviewsAdapter(private val context: Context?) :
    ListAdapter<YelpReview, ReviewViewHolder>(REVIEW_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder =
        ReviewViewHolder.from(parent)

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(context, it)
        }
    }

    companion object {
        private val REVIEW_COMPARATOR = object : DiffUtil.ItemCallback<YelpReview>() {
            override fun areItemsTheSame(
                oldItem: YelpReview,
                newItem: YelpReview
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: YelpReview,
                newItem: YelpReview
            ): Boolean =
                oldItem == newItem
        }
    }
}
