package com.uteke.contactbook.features.userlist.presentation.model

sealed interface Action {
    data object CheckConnectivity : Action
    data object Load : Action
    data object Reload : Action
    data class EmailClick(val uuid: String) : Action
    data class PhoneClick(val uuid: String) : Action
}