package com.whatevrdev.mercedesyelp.ui.states

import com.whatevrdev.domain.entities.YelpRestaurantDetails

sealed class RestaurantDetailsState {
    object Empty: RestaurantDetailsState()
    object Loading: RestaurantDetailsState()
    data class Error(val message: String?) : RestaurantDetailsState()
    data class Success(val restaurantDetails: YelpRestaurantDetails) : RestaurantDetailsState()
}