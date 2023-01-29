package com.whatevrdev.domain.usecases

import com.whatevrdev.domain.interfaces.YelpApiRepository

class YelpUseCases(private val yelpApiRepository: YelpApiRepository) {

    suspend fun searchForRestaurants(latitude: Double, longitude: Double) =
        yelpApiRepository.searchForRestaurants(latitude, longitude)

    suspend fun getRestaurantDetails(id: String) =
        yelpApiRepository.getRestaurantDetails(id)
}