package com.uteke.contactbook.features.userdetails.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.uteke.contactbook.features.common.view.ErrorView
import com.uteke.contactbook.features.userdetails.R

@Composable
internal fun UserDetailsContent(
    modifier: Modifier,
    viewState: ViewState,
    onReload: () -> Unit,
    onEmailClick: () -> Unit,
    onPhoneClick: () -> Unit,
    onLocationClick: () -> Unit,
) {
    Box(
        modifier = modifier,
    ) {
        when(viewState) {
            ViewState.Loading ->
                CircularProgressIndicator(
                    color = Color.Black,
                )
            is ViewState.Error ->
                ErrorView(
                    message = viewState.message,
                    buttonText =  stringResource(id = R.string.userdetails_action_reload),
                    onReload = onReload,
                )
            is ViewState.Content ->
                UserDetailsView(
                    viewState = viewState,
                    onEmailClick = onEmailClick,
                    onPhoneClick = onPhoneClick,
                    onLocationClick = onLocationClick,
                )
        }
    }
}

@Preview
@Composable
private fun UserDetailsContentPreview() =
    MaterialTheme {
        UserDetailsContent(
            modifier = Modifier.fillMaxSize(),
            viewState = ViewState.Content(
                pictureUrl = "https://randomuser.me/api/portraits/large/women/15.jpg",
                username = "yellowbutterfly577",
                gender = "female",
                fullname = "Mrs Frances Herrera",
                nationality = "France",
                dateOfBirth = "1983/05/27",
                age = "40 y/o",
                address = "5740 W Dallas St",
                city = "Wollongong",
                state =  "Australian Capital Territory",
                country = "Australia",
                postcode = "3018",
            ),
            onReload = {},
            onEmailClick = {},
            onPhoneClick = {},
            onLocationClick = {},
        )
    }