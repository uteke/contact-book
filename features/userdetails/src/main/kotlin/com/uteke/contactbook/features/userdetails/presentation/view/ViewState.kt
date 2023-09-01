package com.uteke.contactbook.features.userdetails.presentation.view

import androidx.compose.runtime.Immutable

sealed interface ViewState {
    data object Loading : ViewState

    data class Error(
        val message: String,
    ) : ViewState

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
    ) : ViewState
}