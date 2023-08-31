package com.uteke.contactbook.features.userdetails.presentation

sealed interface UserDetailsAction {
    data class Load(val id: String) : UserDetailsAction
}