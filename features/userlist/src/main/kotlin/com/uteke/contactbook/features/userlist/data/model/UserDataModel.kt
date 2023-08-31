package com.uteke.contactbook.features.userlist.data.model

data class UserDataModel(
    val uuid: String,
    val gender: String,
    val title: String,
    val firstname: String,
    val lastname: String,
    val age: Int,
    val pictureUrl: String,
    val nationality: String,
)