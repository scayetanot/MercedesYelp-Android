package com.whatevrdev.mercedesyelp.ui.viewholders

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.whatevrdev.domain.entities.YelpBusiness
import com.whatevrdev.mercedesyelp.databinding.ItemRestaurantBinding
import com.whatevrdev.mercedesyelp.ui.viewmodels.HomeViewModel

class ListOfRestaurantsViewHolder(val binding: ItemRestaurantBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context?, item: YelpBusiness, viewModel: HomeViewModel) {
        binding.item = item
        binding.viewModel = viewModel
        item.imageUrl?.let { url ->
            val options = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .fitCenter()

            context?.let {
                try {
                    Glide
                        .with(it)
                        .load(url)
                        //.apply(options)
                        .into(binding.restaurantImage)
                } catch (e: Exception) {
                    Log.e("","Thumbnail Failed in Glide...using error drawable resource")
                }
            }
        }
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ListOfRestaurantsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemRestaurantBinding.inflate(layoutInflater, parent, false)
            return ListOfRestaurantsViewHolder(binding)
        }
    }
}