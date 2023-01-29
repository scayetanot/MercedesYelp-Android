package com.whatevrdev.domain.entities

data class YelpReview(
    val id: String?,
    val url: String?,
    val text: String?,
    val rating: Double?,
    val timeCreated: String?,
    val user: YelpUser?
)
