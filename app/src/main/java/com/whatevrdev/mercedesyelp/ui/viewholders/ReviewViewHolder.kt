package com.whatevrdev.mercedesyelp.ui.viewholders

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.whatevrdev.domain.entities.YelpReview
import com.whatevrdev.mercedesyelp.databinding.ItemRestaurantBinding
import com.whatevrdev.mercedesyelp.databinding.ItemRestaurantReviewBinding

class ReviewViewHolder(val binding: ItemRestaurantReviewBinding):
    RecyclerView.ViewHolder(binding.root){

    fun bind(context: Context?, item: YelpReview) {
        binding.item = item
        item.user?.imageUrl.let { url ->
            val options = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .fitCenter()

            context?.let {
                try {
                    Glide
                        .with(it)
                        .load(url)
                        .apply(options)
                        .into(binding.profilePic)
                } catch (e: Exception) {
                    Log.e("","Thumbnail Failed in Glide...using error drawable resource")
                }
            }
        }
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ReviewViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemRestaurantReviewBinding.inflate(layoutInflater, parent, false)
            return ReviewViewHolder(binding)
        }
    }
}