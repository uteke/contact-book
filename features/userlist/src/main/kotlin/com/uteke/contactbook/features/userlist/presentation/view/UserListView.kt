package com.uteke.contactbook.features.userlist.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun UserListView(
    modifier: Modifier = Modifier,
    userStates: List<UserState>,
    onItemClick: (String) -> Unit,
    onEmailClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit,
    onLoadNext: () -> Unit,
) {
    val lazyColumnListState = rememberLazyListState()
    val shouldStartPaginate = remember {
        derivedStateOf { lazyColumnListState.isScrolledToTheEnd() }
    }

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value) {
            onLoadNext()
        }
    }

    LazyColumn(
        modifier = modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 8.dp,
                bottom = 8.dp,
            ),
        state = lazyColumnListState,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = userStates,
            key = { it.uuid }
        ) { userState ->
            UserCarView(
                modifier = Modifier.wrapContentHeight(),
                state = userState,
                onClick = onItemClick,
                onEmailClick = onEmailClick,
                onPhoneClick = onPhoneClick,
            )
        }
    }
}

fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1


@Preview
@Composable
private fun UserListViewPreview() =
    MaterialTheme {
        UserListView(
            modifier = Modifier,
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
            onItemClick = {},
            onEmailClick = {},
            onPhoneClick = {},
            onLoadNext = {},
        )
    }
