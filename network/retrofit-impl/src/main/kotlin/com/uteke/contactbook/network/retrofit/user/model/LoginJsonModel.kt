package com.uteke.contactbook.network.retrofit.user.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginJsonModel(
    val uuid: String,
    val username: String,
)
