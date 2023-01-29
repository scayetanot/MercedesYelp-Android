package com.whatevrdev.data.network
import com.whatevrdev.data.network.models.RetrofitSearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import com.whatevrdev.data.BuildConfig
import com.whatevrdev.data.network.models.RetrofitDetailsResponse
import com.whatevrdev.data.network.models.RetrofitReviewsResponse


@Singleton
class ApiHelperImpl @Inject  constructor(
    private val apiService: ApiService
):ApiHelper {
    override suspend fun searchForRestaurants(
        latitude: Double,
        longitude: Double
    ): ApiResult<RetrofitSearchResponse> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.searchForRestaurants(
                token = "Bearer " + BuildConfig.TOKEN,
                latitude = latitude,
                longitude = longitude,
                limit = 20,
                term = "restaurants",
                radius = 10000,
                attributes = "hot_and_new",
                sort_by = "distance")
            ApiResult.OnSuccess(response)
        } catch (e: Exception) {
            ApiResult.OnError(e)
        }
    }

    override suspend fun getRestaurantDetails(
        id: String
    ): ApiResult<RetrofitDetailsResponse> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getRestaurantDetails(
                token = "Bearer " + BuildConfig.TOKEN,
                id = id
            )
            ApiResult.OnSuccess(response)
        } catch (e: Exception) {
            ApiResult.OnError(e)
        }
    }

    override suspend fun getRestaurantReviews(
        id: String
    ): ApiResult<RetrofitReviewsResponse> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getRestaurantReviews(
                token = "Bearer " + BuildConfig.TOKEN,
                id = id,
                limit = 3,
                sort_by = "yelp_sort"
            )
            ApiResult.OnSuccess(response)
        } catch (e: Exception) {
            ApiResult.OnError(e)
        }
    }

}