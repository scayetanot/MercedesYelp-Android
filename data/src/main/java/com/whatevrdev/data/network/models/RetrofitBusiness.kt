package com.whatevrdev.data.network.models

import com.google.gson.annotations.SerializedName

data class RetrofitBusiness(
    @SerializedName("id") val id: String?,
    @SerializedName("alias") val alias: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("is_closed") val isClosed: Boolean?,
    @SerializedName("url") val url: String?,
    @SerializedName("review_count") val reviewCount: Double?,
    @SerializedName("categories") val categories: List<RetrofitCategory?>,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("coordinates") val coordinates: RetrofitCoordinates?,
    @SerializedName("transactions") val transactions: List<String?>,
    @SerializedName("price") val price: String?,
    @SerializedName("location") val location: RetrofitLocation?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("display_phone") val displayPhone: String?,
    @SerializedName("distance") val distance: Double?,
)
