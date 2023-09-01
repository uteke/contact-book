package com.uteke.contactbook.features.userdetails.presentation.model

sealed interface Action {
    data class Load(val id: String) : Action
    data class EmailClick(val id: String) : Action
    data class PhoneClick(val id: String) : Action
    data class LocationClick(val id: String) : Action
}