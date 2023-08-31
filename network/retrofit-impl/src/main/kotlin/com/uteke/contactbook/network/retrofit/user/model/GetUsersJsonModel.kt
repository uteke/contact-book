package com.uteke.contactbook.network.retrofit.user.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetUsersJsonModel(
    val results: List<UserJsonModel>,
    val info: InfoJsonModel,
)
