package com.uteke.contactbook.network.user.model

data class InfoApiModel(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String,
)
