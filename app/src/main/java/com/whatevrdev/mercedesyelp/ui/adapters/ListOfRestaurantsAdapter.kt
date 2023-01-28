package com.whatevrdev.mercedesyelp.ui.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.whatevrdev.domain.entities.YelpBusiness
import com.whatevrdev.mercedesyelp.ui.viewholders.ListOfRestaurantsViewHolder
import com.whatevrdev.mercedesyelp.ui.viewmodels.HomeViewModel

class ListOfRestaurantsAdapter(private val context: Context?, private val viewModel: HomeViewModel) :
    ListAdapter<YelpBusiness, ListOfRestaurantsViewHolder>(RESTAURANT_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOfRestaurantsViewHolder =
        ListOfRestaurantsViewHolder.from(parent)

    override fun onBindViewHolder(holder: ListOfRestaurantsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(context, it, viewModel)
        }
    }

    companion object {
        private val RESTAURANT_COMPARATOR = object : DiffUtil.ItemCallback<YelpBusiness>() {
            override fun areItemsTheSame(
                oldItem: YelpBusiness,
                newItem: YelpBusiness
            ): Boolean =
                oldItem.name == newItem.name &&
                        oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: YelpBusiness,
                newItem: YelpBusiness
            ): Boolean =
                oldItem == newItem
        }
    }
}