package com.uteke.contactbook.features.userlist.presentation.model

sealed interface Event {
    data class OpenEmail(val email: String) : Event
    data class OpenPhone(val phone: String) : Event
    data class ToastError(val message: String) : Event
}