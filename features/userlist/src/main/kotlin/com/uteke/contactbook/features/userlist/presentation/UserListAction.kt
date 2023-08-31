package com.uteke.contactbook.features.userlist.presentation

sealed interface UserListAction {
    data object Load : UserListAction
    data object Reload : UserListAction
}