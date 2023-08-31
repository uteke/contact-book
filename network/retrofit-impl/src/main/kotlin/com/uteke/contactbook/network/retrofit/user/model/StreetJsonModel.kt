package com.uteke.contactbook.network.retrofit.user.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StreetJsonModel(
    val number: Int,
    val name: String,
)