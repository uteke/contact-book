package com.uteke.contactbook.network.retrofit.user.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DateOfBirthJsonModel(
    val date: String,
    val age: Int,
)
