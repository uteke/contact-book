package com.uteke.contactbook.network.retrofit.user.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NameJsonModel(
    val title: String,
    val first: String,
    val last: String,
)
