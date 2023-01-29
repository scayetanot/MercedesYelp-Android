package com.whatevrdev.data.network.models

import com.google.gson.annotations.SerializedName

data class RetrofitSpecialHours (
    @SerializedName("date") val date: String?,
    @SerializedName("end") val end: String?,
    @SerializedName("is_closed") val isClosed: Boolean?,
    @SerializedName("is_overnight") val isOvernight: Boolean?,
    @SerializedName("start") val start: String?,

)