package com.uteke.contactbook.features.userlist.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uteke.contactbook.features.common.view.ErrorView
import com.uteke.contactbook.features.userlist.R

@Composable
internal fun UserListContent(
    modifier: Modifier = Modifier,
    viewState: ViewState,
    onItemClick: (String) -> Unit,
    onEmailClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit,
    onLoadNext: () -> Unit,
    onReload: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        if (viewState.isErrorVisible) {
            ErrorView(
                message = viewState.errorMessage,
                buttonText = stringResource(id = R.string.userlist_action_reload),
                onReload = onReload,
            )
        }

        if (viewState.isUserListVisible) {
            UserListView(
                modifier = Modifier.fillMaxSize(),
                userStates = viewState.userStates,
                onItemClick = onItemClick,
                onEmailClick = onEmailClick,
                onPhoneClick = onPhoneClick,
                onLoadNext = onLoadNext,
            )
        }

        if (viewState.isLoaderVisible) {
            CircularProgressIndicator(
                color = Color.Black,
            )
        }
    }

    if (viewState.isConnectivityVisible) {
        BannerView(
            modifier = Modifier.padding(
                start = 8.dp,
                end = 8.dp,
                top = 8.dp,
                bottom = 8.dp,
            ),
        )
    }
}

@Preview
@Composable
private fun UserListContentPreview() =
    MaterialTheme {
        UserListContent(
            modifier = Modifier.fillMaxSize(),
            viewState = ViewState(
                isConnectivityVisible = true,
                isLoaderVisible = true,
                isErrorVisible = true,
                isUserListVisible = true,
                userStates = listOf(
                    UserState(
                        uuid = "uuid1",
                        pictureUrl = "https://randomuser.me/api/portraits/thumb/women/27.jpg",
                        gender = "male",
                        fullname = "Mr John Doe",
                        age = "32 ans",
                        flag = "\uD83C\uDDEB\uD83C\uDDF7",
                    ),
                    UserState(
                        uuid = "uuid2",
                        pictureUrl = "https://randomuser.me/api/portraits/thumb/women/27.jpg",
                        gender = "female",
                        fullname = "Mrs Jane Doe",
                        age = "32 ans",
                        flag = "\uD83C\uDDEB\uD83C\uDDF7",
                    ),
                ),
            ),
            onItemClick = {},
            onEmailClick = {},
            onPhoneClick = {},
            onLoadNext = {},
        ) {}
    }

