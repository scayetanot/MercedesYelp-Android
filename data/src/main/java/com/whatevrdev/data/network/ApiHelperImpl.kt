package com.whatevrdev.data.network
import com.whatevrdev.data.network.models.RetrofitSearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import com.whatevrdev.data.BuildConfig
import com.whatevrdev.data.network.models.RetrofitDetailsResponse


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
                "Bearer " + BuildConfig.TOKEN,
                latitude,
                longitude,
                20,
                "restaurants",
                10000,
                "hot_and_new",
        "distance")
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
                "Bearer " + BuildConfig.TOKEN,
                id
            )
            ApiResult.OnSuccess(response)
        } catch (e: Exception) {
            ApiResult.OnError(e)
        }
    }

}