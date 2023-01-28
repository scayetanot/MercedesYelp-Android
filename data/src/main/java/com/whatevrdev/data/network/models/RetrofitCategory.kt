package com.whatevrdev.data.network.models

import com.google.gson.annotations.SerializedName

data class RetrofitCategory(
    @SerializedName("alias") val alias: String,
    @SerializedName("title") val title: String,
)
