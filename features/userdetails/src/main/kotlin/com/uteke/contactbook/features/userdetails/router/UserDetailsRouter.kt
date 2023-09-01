package com.uteke.contactbook.features.userdetails.router

import androidx.compose.runtime.Composable
import com.uteke.contactbook.features.userdetails.presentation.view.UserDetailsScreen
import org.koin.androidx.compose.koinViewModel

data object UserDetailsRouter {
    const val param = "id"
    const val route = "users/{$param}"

    fun route(id: String) = "users/$id"

    @Composable
    fun Screen(
        id: String,
        goBack: () -> Unit,
        openEmail: (String) -> Unit,
        openPhone: (String) -> Unit,
        openLocation: (Double, Double) -> Unit,
    ) =
        UserDetailsScreen(
            viewModel = koinViewModel(),
            id = id,
            onBack = goBack,
            onEmailClick = openEmail,
            onPhoneClick = openPhone,
            onLocationClick = openLocation,
        )
}
