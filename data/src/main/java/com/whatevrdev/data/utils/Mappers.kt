package com.whatevrdev.data.utils

import com.whatevrdev.data.network.models.RetrofitBusiness
import com.whatevrdev.data.network.models.RetrofitSearchResponse
import com.whatevrdev.domain.entities.YelpBusiness
import com.whatevrdev.domain.entities.YelpBusinesses
import kotlin.math.roundToInt

object Mappers {
    fun fromRetrofitSearchResponseToBusinesses(retrofitSearch: RetrofitSearchResponse): YelpBusinesses {
        return YelpBusinesses(fromRetrofitBusinessesToBusiness(retrofitSearch.businesses))
    }

    fun fromRetrofitBusinessesToBusiness(retrofitBusinesses: List<RetrofitBusiness>): List<YelpBusiness> {
        val listOfBusiness: MutableList<YelpBusiness> = mutableListOf()
        retrofitBusinesses.forEach{
            listOfBusiness.add(
                YelpBusiness(
                    id=it.id,
                    name = it.name,
                    rating = it.rating,
                    price = it.price,
                    distance = fromMetersToMiles(it.distance),
                    imageUrl = it.imageUrl
                )
            )
        }
        return listOfBusiness.toList()
    }

    fun fromMetersToMiles(distance: Double?): String {
        var mileage = ""
        distance?.let {
            mileage = String.format("%.2f", (it * 0.000621371192)) + " mi"
        }
        return mileage
    }
}