package com.whatevrdev.mercedesyelp.ui.viewholders

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.whatevrdev.domain.entities.YelpRestaurantDetails
import com.whatevrdev.mercedesyelp.databinding.ItemRestaurantDetailsBinding
import com.whatevrdev.mercedesyelp.ui.viewmodels.DetailsViewModel

class RestaurantDetailsViewHolder(val binding: ItemRestaurantDetailsBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context?, item: YelpRestaurantDetails?) {
        item?.let {
            binding.item = item
            binding.restaurantPhone.isVisible = !item.phoneNumber.isNullOrBlank()
            binding.restaurantPrice.isVisible = !item.price.isNullOrBlank()
            item.imageUrl?.let { url ->
                val options = RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .fitCenter()

                context?.let {
                    try {
                        Glide
                            .with(it)
                            .load(url)
                            .apply(options)
                            .into(binding.restaurantImage)
                    } catch (e: Exception) {
                        Log.e("","Thumbnail Failed in Glide...using error drawable resource")
                    }
                }
            }
            binding.executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): RestaurantDetailsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemRestaurantDetailsBinding.inflate(layoutInflater, parent, false)
            return RestaurantDetailsViewHolder(binding)
        }
    }
}