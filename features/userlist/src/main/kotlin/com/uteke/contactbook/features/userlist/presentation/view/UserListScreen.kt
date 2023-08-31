package com.uteke.contactbook.features.userlist.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.uteke.contactbook.features.common.view.ScreenScaffold
import com.uteke.contactbook.features.userlist.R
import com.uteke.contactbook.features.userlist.presentation.UserListAction
import com.uteke.contactbook.features.userlist.presentation.UserListViewModel
import com.uteke.contactbook.features.userlist.presentation.UserListViewState

@Composable
internal fun UserListScreen(
    viewModel: UserListViewModel,
    onItemClick: (String) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val viewStateFlow = viewModel.viewStateFlow
    val stateFlowLifecycleAware = remember(viewStateFlow, lifecycleOwner) {
        viewStateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val viewState by stateFlowLifecycleAware.collectAsState(
        initial = UserListViewState.Content(isLoading = true),
    )
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.process(action = UserListAction.Load)
        }
    }

    ScreenScaffold(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.userlist_screen_title),
        snackbarHostState = snackbarHostState,
    ) {
        UserListContent(
            modifier = Modifier.fillMaxSize(),
            viewState = viewState,
            onItemClick = onItemClick,
            onLoadNext = { viewModel.process(UserListAction.Load) },
            onReload = { viewModel.process(UserListAction.Reload) },
        )
    }
}

