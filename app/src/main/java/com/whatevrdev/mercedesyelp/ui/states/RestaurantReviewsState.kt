package com.whatevrdev.mercedesyelp.ui.states

import com.whatevrdev.domain.entities.YelpReview

sealed class RestaurantReviewsState {
    object Empty: RestaurantReviewsState()
    object Loading: RestaurantReviewsState()
    data class Error(val message: String?) : RestaurantReviewsState()
    data class Success(val restaurantReviews: List<YelpReview>) : RestaurantReviewsState()
}