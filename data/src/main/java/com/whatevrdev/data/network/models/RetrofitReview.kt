package com.whatevrdev.data.network.models

import com.google.gson.annotations.SerializedName

data class RetrofitReview(
    @SerializedName("id") val id: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("text") val text: String?,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("timeCreated") val timeCreated: String?,
    @SerializedName("user") val user: RetrofitUser?
)
