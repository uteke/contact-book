package com.uteke.contactbook.network.retrofit.user.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InfoJsonModel(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String,
)
