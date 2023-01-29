package com.whatevrdev.data.network.models

import com.google.gson.annotations.SerializedName

data class RetrofitDetailsResponse(
    @SerializedName("alias") val alias: String?,
    @SerializedName("categories") val categories: List<RetrofitCategory?>,
    @SerializedName("coordinates") val coordinates: RetrofitCoordinates?,
    @SerializedName("display_phone") val displayPhone: String?,
    @SerializedName("distance") val distance: Double?,
    @SerializedName("id") val id: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("is_claimed") val isClaimed: Boolean?,
    @SerializedName("is_closed") val isClosed: Boolean?,
    @SerializedName("date_opened") val dateOpened: String?,
    @SerializedName("date_closed") val dateClosed: String?,
    @SerializedName("location") val location: RetrofitLocation?,
    @SerializedName("name") val name: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("photos") val photos: List<String?>,
    @SerializedName("price") val price: String?,
    @SerializedName("rating") val rating: Float?,
    @SerializedName("review_count") val reviewCount: Double?,
    @SerializedName("hours") val hours: RetrofitHours,
    @SerializedName("special_hours") val specialHours: List<RetrofitSpecialHours>,
    @SerializedName("transactions") val transactions: List<String?>,
    @SerializedName("url") val url: String?,
    //@SerializedName("attributes") val attributes: ???,
    @SerializedName("messaging") val messaging: RetrofitMessaging?,
)
