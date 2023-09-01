package com.uteke.contactbook.features.userdetails.presentation.model

sealed interface Event {
    data class OpenEmail(val email: String) : Event
    data class OpenPhone(val phone: String) : Event
    data class OpenLocation(
        val latitude: Double,
        val longitude: Double,
    ) : Event
    data class ToastError(val message: String) : Event
}