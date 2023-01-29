package com.whatevrdev.domain.entities

import android.location.Address

data class YelpRestaurantDetails(
    val id: String?,
    val name: String?,
    val price: String?,
    val imageUrl: String?,
    val rating: Float?,
    val phoneNumber: String?,
    val address: YelpAddress?,
    val addressDisplay: String?
)