package com.whatevrdev.domain.entities

import com.google.gson.annotations.SerializedName

data class YelpAddress(
    val address1: String?,
    val address2: String?,
    val address3: String?,
    val city: String?,
    val zipCode: String?,
    val country: String?,
    val state: String?,
)
