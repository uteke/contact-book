package com.uteke.contactbook.features.userdetails.data.model

import java.time.LocalDate

data class UserDataModel(
    val uuid: String,
    val gender: String,
    val username: String,
    val title: String,
    val firstname: String,
    val lastname: String,
    val age: Int,
    val dateOfBirth: LocalDate,
    val pictureUrl: String,
    val nationality: String,
    val streetNumber: Int,
    val streetName: String,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
)