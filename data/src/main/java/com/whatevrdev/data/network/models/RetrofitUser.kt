package com.whatevrdev.data.network.models

import com.google.gson.annotations.SerializedName

data class RetrofitUser(
    @SerializedName("id") val id: String?,
    @SerializedName("profile_url") val profileUrl: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("name") val name: String?,
)