package com.uteke.contactbook.network.retrofit.user.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserJsonModel(
    val gender: String,
    val name: NameJsonModel,
    @Json(name = "dob")
    val dateOfBirth: DateOfBirthJsonModel,
    val login: LoginJsonModel,
    val picture: PictureJsonModel,
    @Json(name = "nat")
    val nationality: String,
    val location: LocationJsonModel,
    val email: String,
    val phone: String,
    val cell: String,
)
