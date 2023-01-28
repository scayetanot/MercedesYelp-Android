package com.whatevrdev.data.network

import com.whatevrdev.data.network.models.RetrofitSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("businesses/search")
    suspend fun searchForRestaurants(
        @Header("Authorization") token: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("limit") limit: Int,
        @Query("term") term: String,
        @Query("radius") radius: Int,
        @Query("attributes") attributes: String,
        @Query("sort_by") sort_by: String
        ): RetrofitSearchResponse
}