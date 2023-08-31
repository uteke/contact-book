package com.uteke.contactbook.network.retrofit.user.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PictureJsonModel(
    val large: String,
    val medium: String,
    val thumbnail: String,
)
