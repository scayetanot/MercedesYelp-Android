package com.whatevrdev.data.network.models

import com.google.gson.annotations.SerializedName

data class RetrofitCoordinates (
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
)