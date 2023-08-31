package com.uteke.contactbook.features.userlist.presentation

import androidx.compose.runtime.Immutable

sealed class UserListViewState(open val isConnectionLost: Boolean) {
    data class Error(
        override val isConnectionLost: Boolean = false,
        val message: String,
    ) : UserListViewState(isConnectionLost)

    data class Content(
        override val isConnectionLost: Boolean = false,
        val isLoading: Boolean = false,
        val userStates: List<UserState> = emptyList(),
    ) : UserListViewState(isConnectionLost)
}

@Immutable
data class UserState(
    val uuid: String = "",
    val pictureUrl: String = "",
    val gender: String = "",
    val fullname: String = "",
    val age: String,
    var flag: String,
)
