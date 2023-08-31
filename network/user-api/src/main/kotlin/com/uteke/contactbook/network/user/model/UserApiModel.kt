package com.uteke.contactbook.network.user.model

data class UserApiModel(
    val gender: String,
    val name: NameApiModel,
    val dateOfBirth: DateOfBirthApiModel,
    val login: LoginApiModel,
    val picture: PictureApiModel,
    val nationality: String,
    val location: LocationApiModel,
)
