package com.uteke.contactbook.features.userlist.router

import androidx.compose.runtime.Composable
import com.uteke.contactbook.features.userlist.presentation.view.UserListScreen
import org.koin.androidx.compose.koinViewModel

data object UserListRouter {
    const val route = "users"

    @Composable
    fun Screen(
        onItemClick: (String) -> Unit,
        onOpenEmail: (String) -> Unit,
        onOpenPhone: (String) -> Unit,
    ) =
        UserListScreen(
            viewModel = koinViewModel(),
            onItemClick = onItemClick,
            onOpenEmail = onOpenEmail,
            onOpenPhone = onOpenPhone,
        )
}
