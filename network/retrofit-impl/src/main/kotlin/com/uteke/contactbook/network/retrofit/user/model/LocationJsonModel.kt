package com.uteke.contactbook.network.retrofit.user.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationJsonModel(
    val street: StreetJsonModel,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: CoordinatesJsonModel,
)
