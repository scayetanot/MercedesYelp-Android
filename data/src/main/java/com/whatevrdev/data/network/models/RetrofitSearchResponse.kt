package com.whatevrdev.data.network.models

import com.google.gson.annotations.SerializedName

data class RetrofitSearchResponse(
    @SerializedName("businesses") val businesses : List<RetrofitBusiness>
)
