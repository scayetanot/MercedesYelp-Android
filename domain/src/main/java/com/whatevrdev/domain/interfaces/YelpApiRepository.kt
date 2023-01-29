package com.whatevrdev.domain.interfaces

import com.whatevrdev.domain.entities.*

interface YelpApiRepository {
    suspend fun searchForRestaurants(latitude: Double, longitude: Double) : AppResult<YelpBusinesses>
    suspend fun getRestaurantDetails(id: String): AppResult<YelpRestaurantDetails>
    suspend fun getRestaurantReviews(id: String): AppResult<List<YelpReview>>
}