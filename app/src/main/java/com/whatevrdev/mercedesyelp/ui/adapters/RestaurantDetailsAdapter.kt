package com.whatevrdev.mercedesyelp.ui.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.whatevrdev.domain.entities.YelpRestaurantDetails
import com.whatevrdev.mercedesyelp.ui.viewholders.RestaurantDetailsViewHolder
import com.whatevrdev.mercedesyelp.ui.viewmodels.DetailsViewModel

class RestaurantDetailsAdapter(private val context: Context?,
    private val viewModel: DetailsViewModel) :
    RecyclerView.Adapter<RestaurantDetailsViewHolder>() {

    private var item: YelpRestaurantDetails? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantDetailsViewHolder =
        RestaurantDetailsViewHolder.from(parent)

    override fun onBindViewHolder(holder: RestaurantDetailsViewHolder, position: Int) =
        holder.bind(context, item)

    override fun getItemCount(): Int = 1

    fun updateDetails(details: YelpRestaurantDetails){
        item = details
        notifyDataSetChanged()
    }

}