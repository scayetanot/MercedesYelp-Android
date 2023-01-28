package com.whatevrdev.data.network

import com.whatevrdev.data.network.models.RetrofitSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("v3/businesses/search?latitude={latitude}&longitude={longitude}&term=restaurants&attributes=hot_and_new&sort_by=best_match&limit=20")
    suspend fun searchForRestaurants(
        @Header("Authorization") token: String,
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
        ): RetrofitSearchResponse
}