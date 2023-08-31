package com.uteke.contactbook.network.user.model

data class LocationApiModel(
    val street: StreetApiModel,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
)
