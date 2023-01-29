package com.whatevrdev.data.network.models

import com.google.gson.annotations.SerializedName

data class RetrofitMessaging(
    @SerializedName("url") val url: String?,
    @SerializedName("use_case_text") val useCaseText: String?,
)