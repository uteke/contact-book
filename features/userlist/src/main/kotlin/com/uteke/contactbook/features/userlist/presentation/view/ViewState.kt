package com.uteke.contactbook.features.userlist.presentation.view

import androidx.compose.runtime.Immutable

data class ViewState(
    val isConnectivityVisible: Boolean = false,
    val isLoaderVisible: Boolean = false,
    val isErrorVisible: Boolean = false,
    val errorMessage: String = "",
    val isUserListVisible: Boolean = false,
    val userStates: List<UserState> = emptyList(),
)

@Immutable
data class UserState(
    val uuid: String = "",
    val pictureUrl: String = "",
    val gender: String = "",
    val fullname: String = "",
    val age: String,
    var flag: String,
)
