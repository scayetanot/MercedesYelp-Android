package com.whatevrdev.domain.interfaces

import com.whatevrdev.domain.entities.AppResult
import com.whatevrdev.domain.entities.YelpBusiness
import com.whatevrdev.domain.entities.YelpBusinesses

interface YelpApiRepository {
    suspend fun searchForRestaurants(latitude: Double, longitude: Double) : AppResult<YelpBusinesses>
}