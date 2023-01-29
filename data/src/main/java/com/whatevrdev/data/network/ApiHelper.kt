package com.whatevrdev.data.network

import com.whatevrdev.data.network.models.RetrofitDetailsResponse
import com.whatevrdev.data.network.models.RetrofitSearchResponse

interface ApiHelper {
    suspend fun searchForRestaurants(latitude: Double, longitude: Double): ApiResult<RetrofitSearchResponse>
    suspend fun getRestaurantDetails(id: String): ApiResult<RetrofitDetailsResponse>
}