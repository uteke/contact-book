package com.uteke.contactbook.network.user.model

data class GetUsersApiModel(
    val results: List<UserApiModel>,
    val info: InfoApiModel,
)
