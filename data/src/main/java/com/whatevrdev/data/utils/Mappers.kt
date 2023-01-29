package com.whatevrdev.data.utils

import android.location.Address
import com.whatevrdev.data.network.models.RetrofitBusiness
import com.whatevrdev.data.network.models.RetrofitDetailsResponse
import com.whatevrdev.data.network.models.RetrofitReview
import com.whatevrdev.data.network.models.RetrofitSearchResponse
import com.whatevrdev.domain.entities.*
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

    fun fromRetrofitDetailsResponseToDetails(retrofitDetailsResponse: RetrofitDetailsResponse): YelpRestaurantDetails {
        return YelpRestaurantDetails(
            id = retrofitDetailsResponse.id,
            name = retrofitDetailsResponse.name,
            price = retrofitDetailsResponse.price,
            imageUrl = retrofitDetailsResponse.imageUrl,
            rating = retrofitDetailsResponse.rating,
            phoneNumber = retrofitDetailsResponse.displayPhone,
            address = YelpAddress(
                address1 = retrofitDetailsResponse.location?.address1,
                address2 = retrofitDetailsResponse.location?.address2,
                address3 = retrofitDetailsResponse.location?.address3,
                city = retrofitDetailsResponse.location?.city,
                zipCode = retrofitDetailsResponse.location?.zipCode,
                country = retrofitDetailsResponse.location?.country,
                state = retrofitDetailsResponse.location?.state
                )
        )
    }

    fun fromRetrofitReviewsResponseToReviews(retrofitReviewsList: List<RetrofitReview>) : List<YelpReview> {
        val listOfReviews: MutableList<YelpReview> = mutableListOf()
        retrofitReviewsList.forEach {
            listOfReviews.add(
                YelpReview(
                    id = it.id,
                    url = it.url,
                    text = it.text,
                    rating = it.rating,
                    timeCreated =  it.timeCreated,
                    user = YelpUser(
                        id = it.user?.id,
                        imageUrl = it.user?.imageUrl,
                        name = it.user?.name
                    )
                )
            )
        }
        return listOfReviews.toList()
    }

    fun fromMetersToMiles(distance: Double?): String {
        var mileage = ""
        distance?.let {
            mileage = String.format("%.2f", (it * 0.000621371192)) + " mi"
        }
        return mileage
    }
}