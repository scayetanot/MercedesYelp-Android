package com.whatevrdev.data.network.models

import com.google.gson.annotations.SerializedName

data class RetrofitReviewsResponse(
    @SerializedName("possible_languages") val possibleLanguages : List<String?>,
    @SerializedName("reviews") val reviews : List<RetrofitReview>,
    @SerializedName("total") val total : Int?,
)
