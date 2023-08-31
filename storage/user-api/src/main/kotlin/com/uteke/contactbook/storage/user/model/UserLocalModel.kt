package com.uteke.contactbook.storage.user.model

data class UserLocalModel(
    val index: Int,
    val uuid: String,
    val username: String,
    val gender: String,
    val title: String,
    val firstname: String,
    val lastname: String,
    val dateOfBirth: String,
    val age: Int,
    val thumbnailPictureUrl: String,
    val largePictureUrl: String,
    val nationality: String,
    val streetNumber: Int,
    val streetName: String,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
)
