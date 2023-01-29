package com.whatevrdev.data.network.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class RetrofitHours(
    @SerializedName("open") val open: List<RetrofitOpeningHours?>,
    @SerializedName("hours_type") val hoursType: String?,
    @SerializedName("is_open_now") val isOpenNow: Boolean?,
)