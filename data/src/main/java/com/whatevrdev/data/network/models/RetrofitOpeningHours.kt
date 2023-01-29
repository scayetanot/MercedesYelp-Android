package com.whatevrdev.data.network.models

import com.google.gson.annotations.SerializedName

data class RetrofitOpeningHours(
    @SerializedName("is_overnight") val isOvernight: Boolean?,
    @SerializedName("start") val start: String?,
    @SerializedName("end") val end: String?,
    @SerializedName("day") val day: String?,
)
