package com.uteke.contactbook.features.userdetails.presentation

import androidx.compose.runtime.Immutable

sealed interface UserDetailsViewState {
    data object Loading : UserDetailsViewState

    data class Error(
        val message: String,
    ) : UserDetailsViewState

    @Immutable
    data class Content(
        val pictureUrl: String,
        val username: String,
        val fullname: String,
        val gender: String,
        val nationality: String,
        val dateOfBirth: String,
        val age: String,
        val address: String,
        val city: String,
        val state: String,
        val country: String,
        val postcode: String,
    ) : UserDetailsViewState
}