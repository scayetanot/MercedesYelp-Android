package com.whatevrdev.data.repositories

import com.whatevrdev.data.network.ApiHelper
import com.whatevrdev.data.network.ApiResult
import com.whatevrdev.data.utils.Mappers.fromRetrofitSearchResponseToBusinesses
import com.whatevrdev.domain.entities.AppResult
import com.whatevrdev.domain.entities.YelpBusiness
import com.whatevrdev.domain.entities.YelpBusinesses
import com.whatevrdev.domain.interfaces.YelpApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class YelpApiRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper
): YelpApiRepository {
    override suspend fun searchForRestaurants(
        latitude: Double,
        longitude: Double
    ): AppResult<YelpBusinesses> = withContext(Dispatchers.IO) {
        when (val response = apiHelper.searchForRestaurants(latitude, longitude)) {
            is ApiResult.OnSuccess ->
                AppResult.OnSuccess(fromRetrofitSearchResponseToBusinesses(response.data))
            is ApiResult.OnError ->
                AppResult.OnError(response.exception)
            is ApiResult.OnUnauthorized ->
                AppResult.OnError(Exception("Not Authorized"))
        }
    }

}