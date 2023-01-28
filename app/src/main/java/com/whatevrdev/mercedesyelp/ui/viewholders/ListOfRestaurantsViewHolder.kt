package com.whatevrdev.mercedesyelp.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.whatevrdev.domain.entities.YelpBusiness
import com.whatevrdev.mercedesyelp.databinding.ItemRestaurantBinding
import com.whatevrdev.mercedesyelp.ui.viewmodels.HomeViewModel

class ListOfRestaurantsViewHolder(val binding: ItemRestaurantBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: YelpBusiness, viewModel: HomeViewModel) {
        binding.item = item
        binding.viewModel = viewModel
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