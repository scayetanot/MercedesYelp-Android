package com.whatevrdev.mercedesyelp.ui.viewholders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.whatevrdev.mercedesyelp.databinding.ItemRestaurantDetailsBinding
import com.whatevrdev.mercedesyelp.ui.viewmodels.DetailsViewModel

class RestaurantDetailsViewHolder(val binding: ItemRestaurantDetailsBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context?, viewModel: DetailsViewModel) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): RestaurantDetailsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemRestaurantDetailsBinding.inflate(layoutInflater, parent, false)
            return RestaurantDetailsViewHolder(binding)
        }
    }
}