package com.uteke.contactbook.network.retrofit.user.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoordinatesJsonModel(
    val latitude: String,
    val longitude: String,
)
