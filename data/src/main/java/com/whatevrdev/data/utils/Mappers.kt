package com.whatevrdev.data.utils

import com.whatevrdev.data.network.models.RetrofitBusiness
import com.whatevrdev.data.network.models.RetrofitSearchResponse
import com.whatevrdev.domain.entities.YelpBusiness
import com.whatevrdev.domain.entities.YelpBusinesses

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
                    distance = it.distance,
                    imageUrl = it.imageUrl
                )
            )
        }
        return listOfBusiness.toList()
    }
}